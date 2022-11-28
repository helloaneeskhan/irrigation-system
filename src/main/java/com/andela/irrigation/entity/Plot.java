package com.andela.irrigation.entity;

import com.andela.irrigation.dto.PlotDto;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@Data
@Entity
@Table
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Plot {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String plotCode;

  @Column private String description;

  @CreatedDate private LocalDateTime registrationDate;

  @OneToOne(cascade = CascadeType.ALL)
  private PlotArea plotArea;

  @OneToMany(targetEntity = IrrigationTimeSlot.class, mappedBy = "plot", fetch = FetchType.LAZY)
  private List<IrrigationTimeSlot> irrigationDetails;

  public static Plot fromDto(PlotDto plotDto) {
    return Plot.builder()
        .id(plotDto.getId())
        .plotCode(plotDto.getPlotCode())
        .description(plotDto.getDescription())
        .registrationDate(plotDto.getRegistrationDate())
        .plotArea(PlotArea.fromDto(plotDto.getPlotArea()))
        .build();
  }
}
