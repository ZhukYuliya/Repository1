package dao;

import java.util.List;

import dao.exception.DAOException;
import model.Tariff;


/**
 * The Interface TariffDAO.
 */
public interface TariffDAO {
	
	/**
	 * Show tariffs.
	 *
	 * @return the list
	 * @throws DAOException the DAO exception
	 */
	List<Tariff> showTariffs() throws DAOException;
	
	/**
	 * Adds the tariff.
	 *
	 * @param tariff the tariff
	 * @throws DAOException the DAO exception
	 */
	void addTariff(Tariff tariff) throws DAOException;
	
	/**
	 * Update tariff.
	 *
	 * @param tariff the tariff
	 * @throws DAOException the DAO exception
	 */
	void updateTariff(Tariff tariff) throws DAOException;

    /**
     * Gets the tariff by name.
     *
     * @param tariffName the tariff name
     * @return the tariff by name
     * @throws DAOException the DAO exception
     */
    Tariff getTariffByName(String tariffName) throws DAOException;
	
	/**
	 * Gets the tariff by id.
	 *
	 * @param tariffId the tariff id
	 * @return the tariff by id
	 * @throws DAOException the DAO exception
	 */
	Tariff getTariffById(int tariffId) throws DAOException;

}
