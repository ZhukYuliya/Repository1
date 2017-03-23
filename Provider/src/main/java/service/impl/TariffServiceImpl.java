package service.impl;

import java.util.List;

import dao.DAOFactory;
import dao.TariffDAO;
import dao.exception.DAOException;
import model.Tariff;
import service.TariffService;
import service.exception.DuplicateTariffServiceException;
import service.exception.ServiceException;

/**
 * The Class TariffServiceImpl.
 */
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
	public void saveTariff(Tariff tariff, boolean newlyAdded) throws ServiceException, DuplicateTariffServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		TariffDAO tariffDAO = daoFactory.getTariffDAO();
		try {
			if (newlyAdded) {
				if (tariffDAO.getTariffByName(tariff.getName()) == null) {
					tariffDAO.addTariff(tariff);
				} else {
					throw new DuplicateTariffServiceException();
				}
			} else {
				tariffDAO.updateTariff(tariff);
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public Tariff getTariffById(int tariffId) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		TariffDAO tariffDAO = daoFactory.getTariffDAO();
		Tariff tariff = null;
		try {
			tariff = tariffDAO.getTariffById(tariffId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return tariff;
	}

}
