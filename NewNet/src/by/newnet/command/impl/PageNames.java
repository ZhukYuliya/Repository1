package by.newnet.command.impl;

import by.newnet.command.CommandName;

public class PageNames {
	public static final String INDEX = "/index.jsp";
	public static final String HOME = "/home.jsp";
	public static final String ADMIN = "/admin.jsp";
	public static final String TARIFFS = "/tariffs.jsp";
	public static final String OPERATOR = "/operator.jsp";
	public static final String REGISTRATION = "/registration.jsp";
	public static final String PERSONAL_DETAILS = "/personal_details.jsp";
	//context path needed?
	public static final String SHOW_ACCOUNT_COMMAND = "/controller?command=" + CommandName.SHOW_ACCOUNT;
	public static final String CHANGE_PERSONAL_DETAILS_COMMAND = "/controller?command=" + CommandName.CHANGE_PERSONAL_DETAILS;
	public static final String USERS = "/users.jsp";
	public static final String EDIT_USER = "/edit_user.jsp";
	public static final String EDIT_TARIFF = "/edit_tariff.jsp";
    public static final String SHOW_TARIFFS_COMMAND = "/controller?command=" + CommandName.SHOW_TARIFFS;
	public static final String REQUESTS = "/requests.jsp";
	public static final String PAYMENT = "/payment.jsp";
	public static final String SHOW_REQUESTS_COMMAND = "/controller?command=" + CommandName.SHOW_REQUESTS;
	public static final String SHOW_USERS_COMMAND = "/controller?command=" + CommandName.SHOW_USERS;

	private PageNames(){}
	

}
