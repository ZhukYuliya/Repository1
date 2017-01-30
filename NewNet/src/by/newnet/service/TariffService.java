package by.newnet.service;

import java.util.List;

import by.newnet.model.Tariff;
import by.newnet.service.exception.DuplicateTariffServiceException;
import by.newnet.service.exception.ServiceException;

public interface TariffService {
	
	List<Tariff> showTariffs() throws ServiceException;
    void saveTariff(Tariff tariff, boolean newlyAdded) throws ServiceException, DuplicateTariffServiceException;
	Tariff getTariffById(int tariffId)throws ServiceException;
}
