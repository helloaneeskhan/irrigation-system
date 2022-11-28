package com.andela.irrigation.service.impl;

import com.andela.irrigation.dto.PlotDto;
import com.andela.irrigation.entity.Plot;
import com.andela.irrigation.exception.RecordNotFoundException;
import com.andela.irrigation.repository.PlotRepository;
import com.andela.irrigation.service.PlotService;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PlotServiceImpl implements PlotService {

  @Autowired private PlotRepository plotRepository;

  @Override
  public PlotDto getPlotById(Long id) {
    Optional<Plot> plotOptional = plotRepository.findById(id);
    if (!plotOptional.isPresent())
      throw new RecordNotFoundException(String.format("Plot with id %d not found", id));

    return plotOptional.map(PlotDto::fromEntity).get();
  }

  @Override
  public Page<PlotDto> getAllPlot(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<Plot> plotPage = plotRepository.findAll(pageable);
    if (plotPage.getTotalElements() == 0)
      throw new RecordNotFoundException("No plot exists in system");

    return plotPage.map(PlotDto::fromEntity);
  }

  @Override
  public PlotDto savePlot(PlotDto plotDto) {
    plotDto.setRegistrationDate(LocalDateTime.now());
    Plot plot = plotRepository.save(Plot.fromDto(plotDto));
    return PlotDto.fromEntity(plot);
  }

  @Override
  public PlotDto updatePlot(PlotDto plotDto) {
    Optional<Plot> plotOptional = plotRepository.findById(plotDto.getId());
    if (!plotOptional.isPresent())
      throw new RecordNotFoundException(
          String.format("Plot with id %d not found", plotDto.getId()));

    Plot plot = plotRepository.save(Plot.fromDto(plotDto));
    return PlotDto.fromEntity(plot);
  }
}
