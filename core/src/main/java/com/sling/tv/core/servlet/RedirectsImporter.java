package com.sling.tv.core.servlet;

import com.sling.tv.core.services.impl.RedirectsToolServiceImpl;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.jackrabbit.commons.JcrUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.Session;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.rmi.ServerException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//Sling Imports

//This is a component so it can provide or consume services
@SlingServlet(paths = "/bin/customexcelfile", methods = "POST", metatype = true)
public class RedirectsImporter extends SlingAllMethodsServlet {
	private static final long serialVersionUID = 2598426539166789515L;

	// Inject a Sling ResourceResolverFactory
	@Reference
	private ResourceResolverFactory resolverFactory;

	private Session session;

	/** Default log. */
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	private ResourceResolver resolver = null;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RedirectsToolServiceImpl.class);

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServerException, IOException {

	}

	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServerException, IOException {

		try {
			final boolean isMultipart = org.apache.commons.fileupload.servlet.ServletFileUpload
					.isMultipartContent(request);
			PrintWriter out = response.getWriter();

			if (isMultipart) {
				final java.util.Map<String,RequestParameter[]> params = request.getRequestParameterMap();
				for (final java.util.Map.Entry<String,RequestParameter[]> pairs : params.entrySet()) {

					final RequestParameter[] pArr = pairs.getValue();
					final RequestParameter param = pArr[0];
					final InputStream stream = param.getInputStream();

					// Save the uploaded file into the Adobe CQ DAM
					int excelValue = injectSpreadSheet(stream);
					if (excelValue == 0)
						out.println("Redirects has been successfully imported");
					else
						out.println("Redirects could not be imported!, Make sure there is no [redirects] folder under [content] folder before you import");
				}
			}
		}

		catch (Exception e) {
			log.error("Error in Redirects import" + e.getMessage());
		}

	}

	// Get data from the excel spreadsheet

	public int injectSpreadSheet(InputStream is) {
		try {

			HSSFWorkbook workbook = new HSSFWorkbook(is);
			HSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = sheet.rowIterator();
			int countRow = 0;

			while (iterator.hasNext()) {
				countRow++;
				Row currentRow = iterator.next();
				if(countRow==1) continue;
				Iterator<Cell> cellIterator = currentRow.cellIterator();
				Map<String,String> cellValues = new HashMap<String,String>();
				int countCell =0;

				while (cellIterator.hasNext()) {
					countCell++;
					Cell currentCell = cellIterator.next();
					String cellValue = "";
					if (currentCell.getCellTypeEnum() == CellType.STRING) {
						cellValue =  currentCell.getStringCellValue();
					} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
						cellValue = Double.toString(currentCell.getNumericCellValue());
					}

					cellValues.put(Integer.toString(countCell),cellValue);
				}

				int status = injestCustData(cellValues,Integer.toString(countRow));
				if(status==-1) {
					return -1;
				}
			}

			workbook.close();
			return 0;

		} catch (Exception e) {
			log.error("Error in Injecting" + e.getMessage());
			return -1;
		}

	}

	// Stores Redirects data in the Adobe CQ JCR

	public int injestCustData(Map<String,String> cellValues, String rowCell) {
		try {

			// Invoke the adaptTo method to create a Session used to create a
			// QueryManager
			Map<String, Object> serviceParams = new HashMap<>();
			serviceParams.put(ResourceResolverFactory.SUBSERVICE,"RedirectsService");

			try {
				resolver = resolverFactory.getServiceResourceResolver(serviceParams);
			} catch (LoginException e) {
				LOGGER.error("Error in getting resolver " + e);
			}
			session = resolver.adaptTo(Session.class);

			// Create a node that represents the root node
			Node root = session.getRootNode();
			//Get the content node in the JCR
			Node content = root.getNode("content");

			//Determine if the content/customer node exists
			Node customerRoot = null;
			int custRec = doesCustExist(content);


			log.info("*** Value of  custRec is ..." +custRec);
			//-1 means that content/customer does not exist
			if (custRec == -1)
			{
				//content/customer does not exist -- create it
				customerRoot = content.addNode("redirects");
			}
			else
			{
				//content/customer does exist -- retrieve it
				customerRoot = content.getNode("redirects");
			}

			int custId = custRec+1;

			Node redirectsNode = customerRoot.addNode("Redirects" + rowCell, "nt:unstructured");


			// make sure name of node is unique
			redirectsNode.setProperty("source", (String)cellValues.get("1"));
			redirectsNode.setProperty("destination", (String)cellValues.get("2"));
			redirectsNode.setProperty("temporary", (String)cellValues.get("3"));
			redirectsNode.setProperty("legacy", (String)cellValues.get("4"));
			redirectsNode.setProperty("query", (String)cellValues.get("5"));
			redirectsNode.setProperty("created_date", cellValues.get("6"));

			// Save the session changes and log out
			session.save();
			session.logout();
			return 0;
		}
		catch (Exception e) {
			log.error("Error in Redirects import" + e.getMessage());
			return -1;
		}
	}
	private int doesCustExist(Node content)
	{
		try
		{
			int index = 0 ;
			int childRecs = 0 ;

			java.lang.Iterable<Node> custNode = JcrUtils.getChildNodes(content, "redirects");
			Iterator it = custNode.iterator();

			if (it.hasNext())
			{
				Node customerRoot = content.getNode("redirects");
				Iterable itCust = JcrUtils.getChildNodes(customerRoot);
				Iterator childNodeIt = itCust.iterator();

				while (childNodeIt.hasNext())
				{
					childRecs++;
					childNodeIt.next();
				}
				return childRecs;
			}
			else
				return -1;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}

}