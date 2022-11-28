package com.andela.irrigation.controller;

import com.andela.irrigation.constants.AppConstants;
import com.andela.irrigation.dto.PlotDto;
import com.andela.irrigation.service.PlotService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AppConstants.API_VERSION_1 + "/plot")
@RequiredArgsConstructor
@Slf4j
public class PlotController {

  @Autowired private PlotService plotService;

  @GetMapping
  public PlotDto getPlot(@RequestParam("id") Long id) {
    return plotService.getPlotById(id);
  }

  @GetMapping("/list")
  public Page<PlotDto> getAllAvailablePlot(
      @RequestParam(required = false, defaultValue = "0") int page,
      @RequestParam(required = false, defaultValue = "10") int size) {
    return plotService.getAllPlot(page, size);
  }

  @PostMapping("/add")
  public PlotDto addNewPlot(@RequestBody @Valid PlotDto plotDto) {
    return plotService.savePlot(plotDto);
  }

  @PutMapping("/update")
  public PlotDto updatePlot(@RequestBody @Valid PlotDto plotDto) {
    return plotService.updatePlot(plotDto);
  }
}
