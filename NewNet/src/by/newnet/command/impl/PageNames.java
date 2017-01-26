package by.newnet.command.impl;

import by.newnet.command.CommandName;

public class PageNames {
	public static final String INDEX = "/public_jsp/index.jsp";
	public static final String HOME = "/WEB-INF/jsp/customer/home.jsp";
	public static final String ADMIN = "/WEB-INF/jsp/admin/admin.jsp";
	public static final String TARIFFS = "/public_jsp/tariffs.jsp";
	public static final String OPERATOR = "/WEB-INF/jsp/operator/operator.jsp";
	public static final String REGISTRATION = "/WEB-INF/jsp/customer/registration.jsp";
	public static final String PERSONAL_DETAILS = "/WEB-INF/jsp/customer/personal_details.jsp";
	//context path needed?
	public static final String SHOW_ACCOUNT_COMMAND = "/controller?command=" + CommandName.SHOW_ACCOUNT;
	public static final String CHANGE_PERSONAL_DETAILS_COMMAND = "/controller?command=" + CommandName.CHANGE_PERSONAL_DETAILS;
	public static final String USERS = "/WEB-INF/jsp/admin/users.jsp";
	public static final String EDIT_USER = "/WEB-INF/jsp/admin/edit_user.jsp";
	public static final String EDIT_TARIFF = "/WEB-INF/jsp/admin/edit_tariff.jsp";
    public static final String SHOW_TARIFFS_COMMAND = "/controller?command=" + CommandName.SHOW_TARIFFS;
	public static final String REQUESTS = "/WEB-INF/jsp/operator/requests.jsp";
	public static final String PAYMENT = "/WEB-INF/jsp/customer/payment.jsp";
	public static final String SHOW_REQUESTS_COMMAND = "/controller?command=" + CommandName.SHOW_REQUESTS;
	public static final String SHOW_CUSTOMERS_COMMAND = "/controller?command=" + CommandName.SHOW_CUSTOMERS;
	public static final String SHOW_OPERATORS_COMMAND = "/controller?command=" + CommandName.SHOW_OPERATORS;
	public static final String SHOW_ADMINS_COMMAND = "/controller?command=" + CommandName.SHOW_ADMINS;
	public static final String NEW_CONTRACT = "/WEB-INF/jsp/operator/new_contract.jsp";
	public static final String AFTER_POST_REQUEST = "/public_jsp/index.jsp";
	public static final String ERROR_404_PAGE = "/public_jsp/error404.jsp";

	private PageNames(){}
	

}
