package com.sling.tv.core.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.jcr.Session;
import javax.servlet.ServletException;

import com.day.cq.commons.Console;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@SlingServlet(paths = "/bin/MatchInfoServlet", methods = "GET")
public class MatchInfoServlet extends SlingSafeMethodsServlet {
	

    /**
     * comment statements are missing
     */
    private static final long serialVersionUID = 1L;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private String nemee="Aratijena";

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {

        try {
            String excelFilePath = request.getParameter("filePath");
            Resource resourceExcel=request.getResourceResolver().getResource(excelFilePath);
            Asset excelAsset = resourceExcel.adaptTo(Asset.class);
			Rendition rendition=excelAsset.getOriginal();
			InputStream inputStream =rendition.getStream();
            XSSFWorkbook wb = new XSSFWorkbook(inputStream);
            XSSFSheet ws = wb.getSheetAt(0);
            int rowNum = ws.getLastRowNum() + 1;

            ObjectMapper objMapper = new ObjectMapper();
            ObjectNode matches = objMapper.createObjectNode();

            ArrayNode matchesArr = objMapper.createArrayNode();

            for (int i = 1; i < rowNum; i++) {

                ObjectNode match = objMapper.createObjectNode();
                Console.log(i + '--- this is the number');


                XSSFRow row = ws.getRow(i);
                XSSFCell dateCell = row.getCell(0);
//				Format outDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//              Date date = dateCell.getDateCellValue();
                String dateString = dateCell == null ? "" : dateCell.getStringCellValue();

				/*String dateStr =  "";
                if(date != null){
                dateStr = outDateFormat.format(date);
                }
                */

                XSSFCell affiliationCell = row.getCell(1);
                String affiliation = affiliationCell == null ? "" : affiliationCell.getStringCellValue();
                String affiliationLowerCase = affiliation.toLowerCase();
                XSSFCell channelCell = row.getCell(2);
                String channelOne = channelCell == null ? "" : channelCell.getStringCellValue();
                XSSFCell displayDataDesktopCell = row.getCell(3);
                String displayDataDesktop = displayDataDesktopCell == null ? "" : displayDataDesktopCell.getStringCellValue();
                XSSFCell displayDataMobileCell = row.getCell(4);
                String displayDataMobile = displayDataMobileCell == null ? "" : displayDataMobileCell.getStringCellValue();
                XSSFCell searchDataCell = row.getCell(5);
                String searchData = searchDataCell == null ? "" : searchDataCell.getStringCellValue();
                XSSFCell networkCell = row.getCell(6);
                String network = networkCell == null ? "" : networkCell.getStringCellValue();
                XSSFCell drawerDisplayNozipCell = row.getCell(7);
                String drawerDisplayNozip = drawerDisplayNozipCell == null ? "" : drawerDisplayNozipCell.getStringCellValue();
                XSSFCell drawerDisplayZipCell = row.getCell(8);
                String drawerDisplayZip = drawerDisplayZipCell == null ? "" : drawerDisplayZipCell.getStringCellValue();
                XSSFCell channelLinkCell = row.getCell(9);
                String channelLink = channelLinkCell == null ? "" : channelLinkCell.getStringCellValue();

                XSSFCell zip1Cell = row.getCell(10);
                String zip1Str = zip1Cell == null ? "" : zip1Cell.getStringCellValue();
                XSSFCell zip2Cell = row.getCell(11);
                String zip2Str = zip2Cell == null ? "" : zip2Cell.getStringCellValue();
                XSSFCell zip3Cell = row.getCell(12);
                String zip3Str = zip3Cell == null ? "" : zip3Cell.getStringCellValue();
                XSSFCell zip4Cell = row.getCell(13);
                String zip4Str = zip4Cell == null ? "" : zip4Cell.getStringCellValue();
                XSSFCell zip5Cell = row.getCell(14);
                String zip5Str = zip5Cell == null ? "" : zip5Cell.getStringCellValue();
                XSSFCell zip6Cell = row.getCell(15);
                String zip6Str = zip6Cell == null ? "" : zip6Cell.getStringCellValue();
                XSSFCell zip7Cell = row.getCell(16);
                String zip7Str = zip7Cell == null ? "" : zip7Cell.getStringCellValue();
                XSSFCell zip8Cell = row.getCell(17);
                String zip8Str = zip8Cell == null ? "" : zip8Cell.getStringCellValue();
                XSSFCell zip9Cell = row.getCell(18);
                String zip9Str = zip9Cell == null ? "" : zip9Cell.getStringCellValue();
                XSSFCell zip10Cell = row.getCell(19);
                String zip10Str = zip10Cell == null ? "" : zip10Cell.getStringCellValue();
                XSSFCell zip11Cell = row.getCell(20);
                String zip11Str = zip11Cell == null ? "" : zip11Cell.getStringCellValue();
                XSSFCell zip12Cell = row.getCell(21);
                String zip12Str = zip12Cell == null ? "" : zip12Cell.getStringCellValue();
                XSSFCell zip13Cell = row.getCell(22);
                String zip13Str = zip13Cell == null ? "" : zip13Cell.getStringCellValue();
                XSSFCell zip14Cell = row.getCell(23);
                String zip14Str = zip14Cell == null ? "" : zip14Cell.getStringCellValue();
                XSSFCell zip15Cell = row.getCell(24);
                String zip15Str = zip15Cell == null ? "" : zip15Cell.getStringCellValue();
                XSSFCell zip16Cell = row.getCell(25);
                String zip16Str = zip16Cell == null ? "" : zip16Cell.getStringCellValue();
                XSSFCell zip17Cell = row.getCell(26);
                String zip17Str = zip17Cell == null ? "" : zip17Cell.getStringCellValue();
                XSSFCell zip18Cell = row.getCell(27);
                String zip18Str = zip18Cell == null ? "" : zip18Cell.getStringCellValue();
                XSSFCell zip19Cell = row.getCell(28);
                String zip19Str = zip19Cell == null ? "" : zip19Cell.getStringCellValue();
                XSSFCell zip20Cell = row.getCell(29);
                String zip20Str = zip20Cell == null ? "" : zip20Cell.getStringCellValue();
                String zipCodes = zip1Str + "," + zip2Str + "," + zip3Str + "," + zip4Str + "," + zip5Str + "," + zip6Str + "," + zip7Str + "," + zip8Str + "," + zip9Str + "," + zip10Str + "," +
                        zip11Str + "," + zip12Str + "," + zip13Str + "," + zip14Str + "," + zip15Str + "," + zip16Str + "," + zip17Str + "," + zip18Str + "," + zip19Str + "," + zip20Str;

                XSSFCell expirationCell = row.getCell(30);
//				String expiration = expirationCell == null ? "" : String.valueOf((int)expirationCell.getNumericCellValue());
                String expiration = expirationCell == null ? "" : expirationCell.getStringCellValue();

                XSSFCell sortCell = row.getCell(31);
//				String sort = sortCell == null ? "" : String.valueOf((int)sortCell.getNumericCellValue());
                String sort = sortCell == null ? "" : sortCell.getStringCellValue();


                XSSFCell spanishDrawerDisplayNozipCell = row.getCell(32);
                String spanishDrawerDisplayNozip = spanishDrawerDisplayNozipCell == null ? "" : spanishDrawerDisplayNozipCell.getStringCellValue();
                XSSFCell spanishDrawerDisplayZipCell = row.getCell(33);
                String spanishDrawerDisplayZip = spanishDrawerDisplayZipCell == null ? "" : spanishDrawerDisplayZipCell.getStringCellValue();


                match.put("game_datetime", dateString);
                match.put("affiliation", affiliationLowerCase);
                match.put("channel_link", channelLink);
                match.put("network", channelOne);
                match.put("call_sign", network);
                match.put("display_data_desktop", displayDataDesktop);
                match.put("display_data_mobile", displayDataMobile);
                match.put("search_data", searchData);
                match.put("drawer_display_zip", drawerDisplayZip);
                match.put("drawer_display_nozip", drawerDisplayNozip);
                match.put("zip_list", zipCodes);
                match.put("expiration", expiration);
                match.put("sort_order", sort);
                match.put("spanish_drawer_display_zip", spanishDrawerDisplayNozip);
                match.put("spanish_drawer_display_nozip", spanishDrawerDisplayZip);

                matchesArr.add(match);
            }
            matches.put("count", matchesArr.size());
            matches.putPOJO("games", matchesArr);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(matches.toString());
        } catch (Exception ex) {
            log.info("Error in doGet of DownloadFilesServlet ::: ", ex);
        }
    }
}