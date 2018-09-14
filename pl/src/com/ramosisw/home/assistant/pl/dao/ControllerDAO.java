/**
 * 
 */
package com.ramosisw.home.assistant.pl.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.jboss.logging.Logger;

import com.ramosisw.home.assistant.api.to.ControllerType;
import com.ramosisw.home.assistant.pl.entity.Controller;
import com.ramosisw.home.assistant.pl.to.ControllerTO;
import com.ramosisw.jee.web.core.api.BaseErrorCodes;
import com.ramosisw.jee.web.core.api.ex.DLException;
import com.ramosisw.jee.web.core.pl.dao.BaseDAO;
import com.ramosisw.jee.web.core.pl.ex.DAOException;

/**
 * @author jcramos
 *
 */
@Stateless
@LocalBean
public class ControllerDAO extends BaseDAO<Controller, String> {
	static final Logger log = Logger.getLogger(ControllerDAO.class);

	/**
	 * 
	 * @param controller
	 * @throws DLException
	 */
	public void add(Controller controller) throws DLException {
		try {
			this.save(controller);
		} catch (DAOException e) {
			log.error(e);
			throw new DLException(BaseErrorCodes.DL_BASE_CODE, "Error when try to save object");
		}
	}

	/**
	 * 
	 * @return
	 * @throws DLException
	 */
	public List<ControllerType> getControllers() throws DLException {
		try {
			return ControllerTO.getTO(findAll());
		} catch (DAOException e) {
			log.error(e);
			throw new DLException(BaseErrorCodes.DL_BASE_CODE, "Error when try to find all");
		}
	}
}
