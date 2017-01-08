package by.newnet.service;

import java.util.List;

import by.newnet.domain.Tariff;
import by.newnet.service.exception.ServiceException;

public interface TariffService {
	
	void addNewTariff(Tariff tariff) throws ServiceException;
	List<Tariff> showTariffs() throws ServiceException;
}
