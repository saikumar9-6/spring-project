//ICustomerDAO.java (DAO Interface)
package com.nt.dao;

import com.nt.model.Customer;

public interface ICustomerDAO {
    public   int  insert(Customer cust)throws Exception;
}
