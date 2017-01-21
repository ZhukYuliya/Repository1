package by.newnet.dao;

import java.util.List;

import by.newnet.dao.exception.DAOException;
import by.newnet.domain.Tariff;


public interface TariffDAO {
	
	List<Tariff> showTariffs() throws DAOException;
	void addTariff(Tariff tariff) throws DAOException;
	void updateTariff(Tariff tariff) throws DAOException;

    Tariff getTariffByName(String tariffName) throws DAOException;
	Tariff getTariffById(int tariffId) throws DAOException;

}
