package org.jotad.app.confirmation.filters;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.jotad.app.confirmation.configs.MysqlConn;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConexionFilter implements Filter{

    @Inject
    @MysqlConn
    private Connection conn;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try (Connection connRequest = this.conn){
            if (connRequest.getAutoCommit()){
                connRequest.setAutoCommit(false);
            }
            try {
                filterChain.doFilter(servletRequest,servletResponse);
                connRequest.commit();
            }catch (SQLException e){
                connRequest.rollback();
                ((HttpServletResponse)servletResponse).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
