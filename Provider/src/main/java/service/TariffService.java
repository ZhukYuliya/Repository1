package service;

import java.util.List;

import model.Tariff;
import service.exception.DuplicateTariffServiceException;
import service.exception.ServiceException;

/**
 * The Interface TariffService.
 */
public interface TariffService {
	
	/**
	 * Show tariffs.
	 *
	 * @return the list
	 * @throws ServiceException the service exception
	 */
	List<Tariff> showTariffs() throws ServiceException;
    
    /**
     * Save tariff.
     *
     * @param tariff the tariff
     * @param newlyAdded the newly added
     * @throws ServiceException the service exception
     * @throws DuplicateTariffServiceException the duplicate tariff service exception
     */
    void saveTariff(Tariff tariff, boolean newlyAdded) throws ServiceException, DuplicateTariffServiceException;
	
	/**
	 * Gets the tariff by id.
	 *
	 * @param tariffId the tariff id
	 * @return the tariff by id
	 * @throws ServiceException the service exception
	 */
	Tariff getTariffById(int tariffId)throws ServiceException;
}
