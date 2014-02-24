package net.bitpot.railways.models;

import org.jetbrains.annotations.Nullable;

import javax.swing.table.AbstractTableModel;

/**
 * Table models.
 */
public class RouteTableModel extends AbstractTableModel {
    private RouteList myRouteList;
    private RouteList filteredRoutes;
    private RoutesFilter filter;


    public RouteTableModel() {
        myRouteList = new RouteList();
        filteredRoutes = new RouteList();

        filter = new RoutesFilter(this);
        filterChanged();
    }


    public void setRoutes(@Nullable RouteList routes) {
        if (routes == null)
            routes = new RouteList();

        this.myRouteList = routes;
        filterChanged();
    }


    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Path";
            case 1:
                return "Action";
            case 2:
                return "Name";
            default:
                return super.getColumnName(column);
        }
    }


    @Override
    public int getRowCount() {
        return filteredRoutes.size();
    }


    @Override
    public int getColumnCount() {
        return 3;
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Route route = filteredRoutes.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return route;
            case 1:
                return route.getActionText();
            case 2:
                return route.getRouteName();
        }

        return "";
    }


    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0)
            return Route.class;

        return super.getColumnClass(columnIndex);
    }


    public RoutesFilter getFilter() {
        return filter;
    }


    public void filterChanged() {
        // Here we should update model.
        filter.applyFilter(myRouteList, filteredRoutes);
        this.fireTableDataChanged();
    }


    /**
     * Returns route object associated with specified row.
     *
     * @param rowIndex Row index in model.
     */
    public Route getRoute(int rowIndex) {
        return filteredRoutes.get(rowIndex);
    }


    /**
     * Returns total number of routes that was successfully parsed.
     *
     * @return Number of parsed routes.
     */
    public int getTotalRoutesCount() {
        return myRouteList.size();
    }
}