package by.newnet.service;

import java.util.List;

import by.newnet.domain.Tariff;
import by.newnet.service.exception.ServiceException;

public interface TariffService {
	
	List<Tariff> showCatalogue() throws ServiceException;
	void addNewTariff(Tariff tariff) throws ServiceException;
}
