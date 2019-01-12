/**
 * Show refer a friend depending on login & active status
 */
$(document).ready(function() {
  var activeBilledUser = sessionStorage.getItem('newco.activeBilledUser');
  if(activeBilledUser == "true" && isLoggedIn()) {
    $('.js-refer-a-friend').show();
  }
  $('.js-footer-link').each(function() {
    $(this).attr('onClick', "s_objectID='"+$(this).text().trim().replace(' ', '-').toLowerCase()+"-footer-link"+"';");
  });

  $('.js-social-media-icons').each(function() {
    $(this).attr('onClick', "s_objectID='"+$(this).find('span').attr('class').trim().replace('fa fa-', '').toLowerCase()+"-social-media-link"+"';");
  });
});
