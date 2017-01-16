package by.newnet.dao;

import java.util.List;

import by.newnet.dao.exception.DAOException;
import by.newnet.domain.Tariff;


public interface TariffDAO {
	
	List<Tariff> showTariffs() throws DAOException;
	void addTariff(Tariff tariff) throws DAOException;
	void modifyTariff(Tariff tariff) throws DAOException;

    Tariff getTariff(String tariffName) throws DAOException;

}
