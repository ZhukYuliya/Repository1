package by.newnet.service.impl;

import java.util.List;

import by.newnet.dao.DAOFactory;
import by.newnet.dao.TariffDAO;
import by.newnet.dao.exception.DAOException;
import by.newnet.domain.Tariff;
import by.newnet.service.TariffService;
import by.newnet.service.exception.ServiceException;

public class TariffServiceImpl implements TariffService {

	@Override
	public List<Tariff> showTariffs() throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		TariffDAO tariffDAO = daoFactory.getTariffDAO();

		try {
			List<Tariff> tariffsList = tariffDAO.showTariffs();
			return tariffsList;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public void saveTariff(Tariff tariff, boolean newlyAdded) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		TariffDAO tariffDAO = daoFactory.getTariffDAO();

		try {
			if(newlyAdded == true){
				tariffDAO.addTariff(tariff);
			}
			tariffDAO.modifyTariff(tariff);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

    
}
