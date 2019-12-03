package command.admin;

import command.Command;
import domain.Inspector;
import service.InspectorService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

public class ShowInspectors implements Command {
    private final InspectorService inspectorService;

    public ShowInspectors(InspectorService inspectorService) {
        this.inspectorService = inspectorService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = 1;
        int recordsPerPage = 10;
        if (Objects.nonNull(request.getParameter("currentPage"))) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }
        List<Inspector> inspectors = inspectorService.findAll(currentPage, recordsPerPage);

        if (inspectors.isEmpty()) {
            return "/admin/empty-list.jsp";
        }

        request.setAttribute("inspectors_list", inspectors);

        int rows = inspectorService.getRowNumbers();

        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
        return "/admin/inspectors-list.jsp";
    }
}
