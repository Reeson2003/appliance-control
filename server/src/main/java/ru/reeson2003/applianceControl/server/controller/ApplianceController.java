package ru.reeson2003.applianceControl.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.reeson2003.applianceControl.service.api.ApplianceList;
import ru.reeson2003.applianceControl.service.api.ApplianceService;
import ru.reeson2003.applianceControl.server.common.RestConstants;
import ru.reeson2003.applianceControl.server.deserialize.DeserializableAction;
import ru.reeson2003.applianceControl.server.restEntity.RestAppliance;
import ru.reeson2003.applianceControl.server.restEntity.RestApplianceImpl;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Pavel Gavrilov
 */
@RestController
public class ApplianceController implements RestConstants {
    private final ApplianceService applianceService;
    private final ApplianceList applianceList;

    @Autowired
    public ApplianceController(ApplianceService applianceService, ApplianceList applianceList) {
        this.applianceService = applianceService;
        this.applianceList = applianceList;
    }

    @RequestMapping(value = APPLIANCES, method = RequestMethod.GET)
    @ResponseBody
    Collection<RestAppliance> getAppliances(@RequestParam(value = "name", required = false) String name) {
        if (name == null)
            return applianceService.getAppliances().stream()
                    .map(RestApplianceImpl::new)
                    .collect(Collectors.toList());
        return applianceService.getAppliancesByName(name).stream()
                .map(RestApplianceImpl::new)
                .collect(Collectors.toList());
    }

    @RequestMapping(value = APPLIANCE_LIST, method = RequestMethod.GET)
    @ResponseBody
    Collection<String> getApplianceList() {
        return applianceList.getApplianceList();
    }

    @RequestMapping(value = APPLIANCE, method = RequestMethod.GET)
    @ResponseBody
    RestAppliance getApplianceById(@RequestParam Long id) {
        return id != null ? new RestApplianceImpl(applianceService.getApplianceById(id)) : null;
    }

    @RequestMapping(value = APPLIANCE, method = RequestMethod.POST)
    @ResponseBody
    RestAppliance newAppliance(@RequestParam("name") String name) {
        return new RestApplianceImpl(applianceService.addAppliance(name));
    }

    @RequestMapping(value = APPLIANCE, method = RequestMethod.DELETE)
    void removeAppliance(@RequestParam("id") Long id) {
        applianceService.removeAppliance(id);
    }

    @RequestMapping(value = APPLIANCE, method = RequestMethod.PUT)
    void performAction(@RequestParam("id") Long id, @RequestBody DeserializableAction action) {
        applianceService.performAction(id, action);
    }
}
