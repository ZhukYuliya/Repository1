package by.newnet.dao;

import java.util.List;

import by.newnet.dao.exception.DAOException;
import by.newnet.domain.Tariff;


public interface TariffDAO {
	
	List<Tariff> showAllTariffs() throws DAOException;
	void addTariff(Tariff tariff) throws DAOException;

}
