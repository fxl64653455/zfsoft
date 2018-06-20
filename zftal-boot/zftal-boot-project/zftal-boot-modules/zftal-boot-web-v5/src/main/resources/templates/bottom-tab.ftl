<!-- footer --> 
<div id="footerID" class="footer hidden-sm">
	<p>${home.copyright} ${home.company}<br/>${home.addr}</p>
</div>
<!-- footer-end -->
<script type="text/javascript">
jQuery(function($) {
	var pageHeight = $(window).height() - $('#navbar_container').outerHeight(true) - $('#footerID').outerHeight(true);
	$('#bodyContainer').css("min-height",pageHeight);
});
</script>