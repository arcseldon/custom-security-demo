package com.become.bid.util;

import org.hibernate.dialect.MySQLInnoDBDialect;

/**
 * Sets the default charset to UTF-8.
 * 
 * @author arcseldon
 */
public class MySQLUTF8InnoDBDialect extends MySQLInnoDBDialect {

    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }

}
