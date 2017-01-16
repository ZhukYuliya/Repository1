package by.newnet.command.impl;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.domain.Tariff;
import by.newnet.service.TariffService;
import by.newnet.service.ServiceFactory;
import by.newnet.service.exception.ServiceException;

public class SaveTariff implements Command {
	
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PRICE = "price";
    private static final String SPEED = "speed";
    private static final String TRAFFIC = "traffic";
    private static final String INACTIVE = "inactive";

    private static final String SET_TARIFF_MESSAGE = "setTariffMessage";
//TODO: change to set tariff and use both for adding and changing tariff?
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
    	
    	String idParameter;
        String name;
        String priceParameter;
        String speedParameter;
        String trafficParameter;
        
        boolean newlyAdded;
        
        idParameter = request.getParameter(ID);
        int id = 0;
        if(idParameter == null){
        	newlyAdded = true;
        } else{
        	id = Integer.valueOf(idParameter);
        	newlyAdded = false;
        }
        name = request.getParameter(NAME);
        priceParameter = request.getParameter(PRICE);
        speedParameter = request.getParameter(SPEED);
        trafficParameter = request.getParameter(TRAFFIC);
        
        String message = checkIfEmpty(name, priceParameter, speedParameter, trafficParameter);
  
        BigDecimal price = null;
        int speed = 0;
        int traffic = 0;
        boolean inactive = false;
        try{
        price = new BigDecimal(request.getParameter(PRICE));
        speed = Integer.valueOf(request.getParameter(SPEED));
        traffic = Integer.valueOf(request.getParameter(TRAFFIC));
        inactive = Boolean.valueOf(request.getParameter(INACTIVE));
        } catch (NumberFormatException e){
            message = "incorrect input";
        }
        
        if (message == null) {
            Tariff tariff = new Tariff();
            tariff.setId(id);
            tariff.setName(name);
            tariff.setPrice(price);
            tariff.setSpeed(speed);
            tariff.setTraffic(traffic);
            tariff.setInactive(inactive);
            TariffService TariffService = ServiceFactory.getInstance().getTariffService();

            try {
                TariffService.saveTariff(tariff, newlyAdded);
                message = "tariff_saved";
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
        }

        request.setAttribute(SET_TARIFF_MESSAGE, message);

        return PageNames.SHOW_TARIFFS_COMMAND;
    }

    private String checkIfEmpty(String name, String price, String speed, String traffic) {
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(speed) || StringUtils.isEmpty(traffic)) {
            return "empty_fields";
        }
        return null;
    }
}
