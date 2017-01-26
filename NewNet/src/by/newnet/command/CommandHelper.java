package by.newnet.command;

import java.util.HashMap;
import java.util.Map;

import by.newnet.command.exception.IllegalCommandException;
import by.newnet.command.impl.AuthenticationCommand;
import by.newnet.command.impl.ChangeLocaleCommand;
import by.newnet.command.impl.ChangePersonalDetailsCommand;
import by.newnet.command.impl.CheckAccountCommand;
import by.newnet.command.impl.LogOutCommand;
import by.newnet.command.impl.PaymentCommand;
import by.newnet.command.impl.PostRequestCommand;
import by.newnet.command.impl.RegisterNewContractCommand;
import by.newnet.command.impl.RegistrationCommand;
import by.newnet.command.impl.SaveNewContractCommand;
import by.newnet.command.impl.SaveTariffCommand;
import by.newnet.command.impl.SaveUserCommand;
import by.newnet.command.impl.SetContactsCommand;
import by.newnet.command.impl.SetPasswordCommand;
import by.newnet.command.impl.SetRequestStatusCommand;
import by.newnet.command.impl.ShowAccountCommand;
import by.newnet.command.impl.ShowAdminsCommand;
import by.newnet.command.impl.ShowAllTariffsCommand;
import by.newnet.command.impl.ShowCustomersCommand;
import by.newnet.command.impl.ShowOperatorsCommand;
import by.newnet.command.impl.ShowRequestsCommand;
import by.newnet.command.impl.ShowTariffCommand;
import by.newnet.command.impl.ShowUserCommand;
import by.newnet.command.impl.SubscriptionCommand;
import by.newnet.command.impl.ToPaymentCommand;

public class CommandHelper {
	private Map<CommandName, Command> commands = new HashMap<>();

	public CommandHelper() {
		commands.put(CommandName.AUTHENTICATION, new AuthenticationCommand());
		commands.put(CommandName.REGISTRATION, new RegistrationCommand());
		commands.put(CommandName.CHECK_ACCOUNT, new CheckAccountCommand());
		commands.put(CommandName.SAVE_NEW_CONTRACT, new SaveNewContractCommand());
		commands.put(CommandName.REGISTER_NEW_CONTRACT, new RegisterNewContractCommand());
		commands.put(CommandName.SHOW_TARIFFS, new ShowAllTariffsCommand());
		commands.put(CommandName.SHOW_TARIFF, new ShowTariffCommand());
		commands.put(CommandName.SHOW_CUSTOMERS, new ShowCustomersCommand());
		commands.put(CommandName.SHOW_OPERATORS, new ShowOperatorsCommand());
		commands.put(CommandName.SHOW_ADMINS, new ShowAdminsCommand());
		commands.put(CommandName.SHOW_USER, new ShowUserCommand());
		commands.put(CommandName.SAVE_USER, new SaveUserCommand());
		commands.put(CommandName.SET_REQUEST_STATUS, new SetRequestStatusCommand());
		commands.put(CommandName.CHANGE_LOCALE, new ChangeLocaleCommand());
		commands.put(CommandName.LOG_OUT, new LogOutCommand());
		commands.put(CommandName.SHOW_REQUESTS, new ShowRequestsCommand());
		commands.put(CommandName.SHOW_ACCOUNT, new ShowAccountCommand());
		commands.put(CommandName.CHANGE_PERSONAL_DETAILS, new ChangePersonalDetailsCommand());
		commands.put(CommandName.SET_PASSWORD, new SetPasswordCommand());
		commands.put(CommandName.SET_CONTACTS, new SetContactsCommand());
		commands.put(CommandName.PAY, new PaymentCommand());
		commands.put(CommandName.TO_PAYMENT, new ToPaymentCommand());
		commands.put(CommandName.POST_REQUEST, new PostRequestCommand());
		commands.put(CommandName.SUBSCRIBE, new SubscriptionCommand());
		commands.put(CommandName.SAVE_TARIFF, new SaveTariffCommand());
	}

	public Command getCommand(String name) throws IllegalCommandException {
		Command command = null;
		try {
			CommandName commandName = CommandName.valueOf(name.toUpperCase());
			command = commands.get(commandName);
		} catch (IllegalArgumentException | NullPointerException e) {
			throw new IllegalCommandException(e);
		}
		return command;
	}
}
