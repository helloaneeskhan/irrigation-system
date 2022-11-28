package com.andela.irrigation.service.impl;

import com.andela.irrigation.dto.IrrigationTimeSlotDto;
import com.andela.irrigation.dto.IrrigationTimeSlotRequestDto;
import com.andela.irrigation.entity.IrrigationTimeSlot;
import com.andela.irrigation.entity.Plot;
import com.andela.irrigation.exception.RecordNotFoundException;
import com.andela.irrigation.repository.IrrigationTimeSlotRepository;
import com.andela.irrigation.repository.PlotRepository;
import com.andela.irrigation.service.IrrigationTimeSlotService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IrrigationTimeSlotServiceImpl implements IrrigationTimeSlotService {

  @Autowired private IrrigationTimeSlotRepository irrigationTimeSlotRepository;

  @Autowired private PlotRepository plotRepository;

  @Override
  public IrrigationTimeSlotDto getIrrigationTimeSlotById(Long id) {
    Optional<IrrigationTimeSlot> timeSlotOpt = irrigationTimeSlotRepository.findById(id);
    if (!timeSlotOpt.isPresent())
      throw new RecordNotFoundException(
          String.format("Irrigation Time Slot with id %d not found", id));

    return timeSlotOpt.map(IrrigationTimeSlotDto::fromEntity).get();
  }

  @Override
  public Page<IrrigationTimeSlotDto> getAllIrrigationTimeSlotByPlotId(
      Long plotId, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<IrrigationTimeSlot> irrigationTimeSlots =
        irrigationTimeSlotRepository.findAllByPlot_id(plotId, pageable);
    if (irrigationTimeSlots.getTotalElements() == 0)
      throw new RecordNotFoundException("No Irrigation Time Slot exists in system");

    return irrigationTimeSlots.map(IrrigationTimeSlotDto::fromEntity);
  }

  @Override
  public IrrigationTimeSlotDto saveIrrigationTimeSlotDto(
      IrrigationTimeSlotRequestDto irrigationTimeSlotRequestDto) {
    Optional<Plot> plotOptional = plotRepository.findById(irrigationTimeSlotRequestDto.getPlotId());
    Plot plot =
        plotOptional.orElseThrow(
            () ->
                new RecordNotFoundException(
                    String.format(
                        "Plot with id %d not found", irrigationTimeSlotRequestDto.getPlotId())));

    IrrigationTimeSlot irrigationTimeSlot =
        IrrigationTimeSlot.fromRequestDto(irrigationTimeSlotRequestDto);
    irrigationTimeSlot.setPlot(plot);
    irrigationTimeSlot = irrigationTimeSlotRepository.save(irrigationTimeSlot);
    return IrrigationTimeSlotDto.fromEntity(irrigationTimeSlot);
  }

  @Override
  public IrrigationTimeSlotDto updateIrrigationTimeSlotDto(
      IrrigationTimeSlotDto irrigationTimeSlotDto) {
    Optional<IrrigationTimeSlot> irrigationTimeSlotOpt =
        irrigationTimeSlotRepository.findById(irrigationTimeSlotDto.getId());

    if (!irrigationTimeSlotOpt.isPresent())
      throw new RecordNotFoundException(
          String.format(
              "Irrigation Time Slot with id %d not found", irrigationTimeSlotDto.getId()));

    IrrigationTimeSlot irrigationTimeSlot = IrrigationTimeSlot.fromDto(irrigationTimeSlotDto);
    irrigationTimeSlot.setPlot(irrigationTimeSlotOpt.get().getPlot());

    irrigationTimeSlot = irrigationTimeSlotRepository.save(irrigationTimeSlot);
    return IrrigationTimeSlotDto.fromEntity(irrigationTimeSlot);
  }
}
