<html>
 <%@include file="/libs/foundation/global.jsp"%>
<cq:includeClientLib categories="cq.jquery" />
<script type="text/javascript">
  
jQuery(function ($) {

      });

</script>
  
<body>
<div>
<h2>Upload an Excel file that contains Redirects data to the Adobe</h2>
        <p id="support-notice">Your browser does not support Ajax uploads :-(The form will be submitted as normal.</p>
  
        <!-- The form starts -->
        <form action="/" method="POST" enctype="multipart/form-data" id="form-id">

            <!-- The file to upload -->
            <p><input id="file-id" type="file" name="our-file" />
  
                <!--
                  Also by default, we disable the upload button.
                  If Ajax uploads are supported we'll enable it.
                -->
                <input type="button" value="Upload" id="upload-button-id" disabled="disabled" /></p>
             
            <script>
                // Function that will allow us to know if Ajax uploads are supported
                function supportAjaxUploadWithProgress() {
                    return supportFileAPI() && supportAjaxUploadProgressEvents() && supportFormData();
  
                    // Is the File API supported?
                    function supportFileAPI() {
                        var fi = document.createElement('INPUT');
                        fi.type = 'file';
                        return 'files' in fi;
                    };
  
                    // Are progress events supported?
                    function supportAjaxUploadProgressEvents() {
                        var xhr = new XMLHttpRequest();
                        return !! (xhr && ('upload' in xhr) && ('onprogress' in xhr.upload));
                    };
  
                    // Is FormData supported?
                    function supportFormData() {
                        return !! window.FormData;
                    }
                }
  
                // Actually confirm support
                if (supportAjaxUploadWithProgress()) {
                    // Ajax uploads are supported!
                    // Change the support message and enable the upload button
                    var notice = document.getElementById('support-notice');
                    var uploadBtn = document.getElementById('upload-button-id');
                    notice.innerHTML = "Your browser supports HTML uploads to AEM.";
                    uploadBtn.removeAttribute('disabled');
  
                    // Init the Ajax form submission
                    initFullFormAjaxUpload();
  
                    // Init the single-field file upload
                    initFileOnlyAjaxUpload();
                }
  
                function initFullFormAjaxUpload() {
                    var form = document.getElementById('form-id');
                    form.onsubmit = function() {
                        // FormData receives the whole form
                        var formData = new FormData(form);
  
                        // We send the data where the form wanted
                        var action = form.getAttribute('action');
  
                        // Code common to both variants
                        sendXHRequest(formData, action);
  
                        // Avoid normal form submission
                        return false;
                    }
                }
  
                function initFileOnlyAjaxUpload() {
                    var uploadBtn = document.getElementById('upload-button-id');
                    uploadBtn.onclick = function (evt) {
                        var formData = new FormData();
  
                        // Since this is the file only, we send it to a specific location
                        //   var action = '/upload';
  
                        // FormData only has the file
                        var fileInput = document.getElementById('file-id');
                        var file = fileInput.files[0];
                        formData.append('our-file', file);
  
                        // Code common to both variants
                        sendXHRequest(formData);
                    }
                }
  
                // Once the FormData instance is ready and we know
                // where to send the data, the code is the same
                // for both variants of this technique
                function sendXHRequest(formData) {
  
                    var test = 0; 
  
                    $.ajax({
                        type: 'POST',    
                        url:'/bin/customexcelfile',
                        processData: false,  
                        contentType: false,  
                        data:formData,
                        success: function(msg){
                          alert(msg); //display the data returned by the servlet
                        }
                    });
                      
                }
  
                // Handle the start of the transmission
                function onloadstartHandler(evt) {
                    var div = document.getElementById('upload-status');
                    div.innerHTML = 'Upload started!';
                }
  
                // Handle the end of the transmission
                function onloadHandler(event) {
                    //Refresh the URL for Form Preview
                    var msg = event.target.responseText;
  
                   alert(msg);
                }
  
                // Handle the progress
                function onprogressHandler(evt) {
                    var div = document.getElementById('progress');
                    var percent = evt.loaded/evt.total*100;
                    div.innerHTML = 'Progress: ' + percent + '%';
                }
  
                // Handle the response from the server
                function onreadystatechangeHandler(evt) {
                    var status = null;
  
                    try {
                        status = evt.target.status;
                    }
                    catch(e) {
                        return;
                    }
  
                    if (status == '200' && evt.target.responseText) {
                        var result = document.getElementById('result');
                        result.innerHTML = '<p>The server saw it as:</p><pre>' + evt.target.responseText + '</pre>';
                    }
                }
            </script>
  
            <!-- Placeholders for messages set by event handlers -->
            <p id="upload-status"></p>
            <p id="progress"></p>
            <pre id="result"></pre>
  
        </form>
  
</div>
  

</body>
</html>