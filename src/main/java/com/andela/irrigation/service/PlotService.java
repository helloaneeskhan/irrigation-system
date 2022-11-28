package com.andela.irrigation.service;

import com.andela.irrigation.dto.PlotDto;
import org.springframework.data.domain.Page;

public interface PlotService {
  PlotDto getPlotById(Long id);

  Page<PlotDto> getAllPlot(int page, int size);

  PlotDto savePlot(PlotDto plotDto);

  PlotDto updatePlot(PlotDto plotDto);
}
