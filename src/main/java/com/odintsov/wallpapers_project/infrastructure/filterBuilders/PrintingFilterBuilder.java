package com.odintsov.wallpapers_project.infrastructure.filterBuilders;

import com.google.cloud.firestore.Query;
import com.odintsov.wallpapers_project.application.dtos.Printing.PrintingFilter;

public class PrintingFilterBuilder extends BaseProductFirestoreFilterBuilder<PrintingFilter> {

    @Override
    public Query apply(Query baseQuery, PrintingFilter filter) {
        Query query = super.apply(baseQuery, filter);
        if (filter.getPrintMethod() != null && !filter.getPrintMethod().isBlank()) {
            query = query.whereEqualTo("printMethod", filter.getPrintMethod());
        }

        return query;
    }
}
