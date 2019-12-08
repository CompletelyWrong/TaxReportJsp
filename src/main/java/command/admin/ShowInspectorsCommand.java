package command.admin;

import command.Command;
import domain.Inspector;
import service.InspectorService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowInspectorsCommand implements Command {
    private final InspectorService inspectorService;

    public ShowInspectorsCommand(InspectorService inspectorService) {
        this.inspectorService = inspectorService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = parseIntParameter(request, "currentPage");
        int recordsPerPage = parseRecordsPerPage(request, request.getParameter("recordsPerPage"));
        List<Inspector> inspectors = inspectorService.findAll(currentPage, recordsPerPage);

        if (inspectors.isEmpty()) {
            return "/admin/empty-list.jsp";
        }

        request.setAttribute("inspectors", inspectors);
        int rows = inspectorService.getRowNumbers();
        paginating(request, rows, currentPage, recordsPerPage);

        return "/admin/inspectors-list.jsp";
    }
}
