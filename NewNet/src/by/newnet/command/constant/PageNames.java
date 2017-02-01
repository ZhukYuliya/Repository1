package by.newnet.command.constant;

import by.newnet.command.CommandName;

/**
 * The Class PageNames containing constants for referring to pages in commands.
 */
public final class PageNames {
	public static final String INDEX = "/public_jsp/index.jsp";
	public static final String HOME = "/WEB-INF/jsp/customer/home.jsp";
	public static final String ADMIN = "/WEB-INF/jsp/admin/admin.jsp";
	public static final String TARIFFS = "/WEB-INF/jsp/tariffs.jsp";
	public static final String OPERATOR = "/WEB-INF/jsp/operator/operator.jsp";
	public static final String TO_REGISTRATION_COMMAND = "/controller?command=" + CommandName.TO_REGISTRATION;
	public static final String REGISTRATION = "/WEB-INF/jsp/customer/registration.jsp";
	public static final String PERSONAL_DETAILS = "/WEB-INF/jsp/customer/personal_details.jsp";
	public static final String SHOW_ACCOUNT_COMMAND = "/controller?command=" + CommandName.SHOW_ACCOUNT;
	public static final String TO_INDEX_COMMAND = "/controller?command=" + CommandName.TO_INDEX;
	public static final String TO_PERSONAL_DETAILS_COMMAND = "/controller?command=" + CommandName.TO_PERSONAL_DETAILS;
	public static final String USERS = "/WEB-INF/jsp/admin/users.jsp";
	public static final String EDIT_USER = "/WEB-INF/jsp/admin/edit_user.jsp";
	public static final String EDIT_TARIFF = "/WEB-INF/jsp/admin/edit_tariff.jsp";
    public static final String SHOW_TARIFFS_COMMAND = "/controller?command=" + CommandName.SHOW_TARIFFS;
	public static final String REQUESTS = "/WEB-INF/jsp/operator/requests.jsp";
	public static final String PAYMENT = "/WEB-INF/jsp/customer/payment.jsp";
	public static final String SHOW_REQUESTS_COMMAND = "/controller?command=" + CommandName.SHOW_REQUESTS;
	public static final String SHOW_USERS_COMMAND = "/controller?command=" + CommandName.SHOW_USERS;
	public static final String NEW_CONTRACT = "/WEB-INF/jsp/operator/new_contract.jsp";
	public static final String REGISTER_NEW_CONTRACT_COMMAND = "/controller?command=" + CommandName.TO_NEW_CONTRACT;
	public static final String AFTER_POST_REQUEST = "/public_jsp/index.jsp";
	public static final String ERROR_404_PAGE = "/public_jsp/error404.jsp";
	public static final String ERROR_PAGE = "/public_jsp/error.jsp";
	public static final String JS_MESSAGES = "resources/js/js_messages.jsp";
	
	/**
	 * Instantiates a new page names.
	 */
	private PageNames(){}
	

}
