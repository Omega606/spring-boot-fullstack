package com.skynet.customer;

import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerRowMapperTest {

    @Test
    void mapRow() throws SQLException {
        CustomerRowMapper customerRowMapper = new CustomerRowMapper();

        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("name")).thenReturn("Clark");
        when(resultSet.getString("email")).thenReturn("clark@email.com");
        when(resultSet.getInt("age")).thenReturn(20);
        when(resultSet.getString("gender")).thenReturn("Male");

        Customer actual = customerRowMapper.mapRow(resultSet, 1);

        Customer expected = new Customer(
                1, "Clark", "clark@email.com", 20,
                Gender.Male);

        assertThat(actual).isEqualTo(expected);
    }
}