package by.newnet.command.impl;

import by.newnet.command.CommandName;

public class PageNames {
	public static final String INDEX = "/public_jsp/index.jsp";
	public static final String HOME = "/WEB-INF/jsp/home.jsp";
	public static final String ADMIN = "/WEB-INF/jsp/admin.jsp";
	public static final String TARIFFS = "/public_jsp/tariffs.jsp";
	public static final String OPERATOR = "/WEB-INF/jsp/operator.jsp";
	public static final String REGISTRATION = "/WEB-INF/jsp/registration.jsp";
	public static final String PERSONAL_DETAILS = "/WEB-INF/jsp/personal_details.jsp";
	//context path needed?
	public static final String SHOW_ACCOUNT_COMMAND = "/controller?command=" + CommandName.SHOW_ACCOUNT;
	public static final String CHANGE_PERSONAL_DETAILS_COMMAND = "/controller?command=" + CommandName.CHANGE_PERSONAL_DETAILS;
	public static final String USERS = "/WEB-INF/jsp/users.jsp";
	public static final String EDIT_USER = "/WEB-INF/jsp/edit_user.jsp";
	public static final String EDIT_TARIFF = "/WEB-INF/jsp/edit_tariff.jsp";
    public static final String SHOW_TARIFFS_COMMAND = "/controller?command=" + CommandName.SHOW_TARIFFS;
	public static final String REQUESTS = "/WEB-INF/jsp/requests.jsp";
	public static final String PAYMENT = "/WEB-INF/jsp/payment.jsp";
	public static final String SHOW_REQUESTS_COMMAND = "/controller?command=" + CommandName.SHOW_REQUESTS;
	public static final String SHOW_USERS_COMMAND = "/controller?command=" + CommandName.SHOW_USERS;
	public static final String NEW_CONTRACT = "/WEB-INF/jsp/new_contract.jsp";
	public static final String AFTER_POST_REQUEST = "/WEB-INF/jsp/index.jsp";

	private PageNames(){}
	

}
