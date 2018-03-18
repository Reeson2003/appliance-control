package ru.reeson2003.applianceControl.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.reeson2003.applianceConntrol.service.api.ApplianceEntity;
import ru.reeson2003.applianceConntrol.service.api.ApplianceList;
import ru.reeson2003.applianceConntrol.service.api.ApplianceService;
import ru.reeson2003.applianceControl.api.Action;
import ru.reeson2003.applianceControl.server.common.RestConstants;

import java.util.Collection;

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
    Collection<ApplianceEntity> getAppliances(@RequestParam(value = "name", required = false) String name) {
        if (name == null)
            return applianceService.getAppliances();
        return applianceService.getAppliancesByName(name);
    }

    @RequestMapping(value = APPLIANCE, method = RequestMethod.GET)
    ApplianceEntity getApplianceById(Long id) {
        return applianceService.getApplianceById(id);
    }

    @RequestMapping(value = APPLIANCE_LIST, method = RequestMethod.GET)
    Collection<String> getApplianceList() {
        return applianceList.getApplianceList();
    }

    @RequestMapping(value = APPLIANCE, method = RequestMethod.POST)
    ApplianceEntity newAppliance(@RequestParam("name") String name) {
        return applianceService.addAppliance(applianceList.newAppliance(name));
    }

    @RequestMapping(value = APPLIANCE, method = RequestMethod.DELETE)
    void removeAppliance(@RequestParam("id") Long id) {
        applianceService.removeAppliance(id);
    }

    @RequestMapping(value = APPLIANCE, method = RequestMethod.PUT)
    void performAction(@RequestParam("id") Long id, @RequestBody Action action) {
        applianceService.performAction(id, action);
    }
}
