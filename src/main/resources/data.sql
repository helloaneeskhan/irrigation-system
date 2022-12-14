--script table PLOT

INSERT INTO "PUBLIC"."PLOT" VALUES
(1, 'description', '1234', TIMESTAMP '2022-11-28 04:52:55.235', 1),
(2, 'description', '1234', TIMESTAMP '2022-11-28 04:53:41.772', 2),
(3, 'description', '1234', TIMESTAMP '2022-11-28 04:53:42.635', 3),
(4, 'description', '1234', TIMESTAMP '2022-11-28 04:53:43.434', 4);


INSERT INTO "PUBLIC"."PLOT_AREA" VALUES
(1, 12121.00, 0),
(2, 1331.00, 1),
(3, 891.00, 2),
(4, 112.00, 1);

INSERT INTO "PUBLIC"."IRRIGATION_TIME_SLOT" VALUES
(1, NULL, 0, TIMESTAMP '2022-10-11 11:00:00', NULL, 1, 1),
(2, NULL, 0, TIMESTAMP '2022-10-11 11:00:00', NULL, 1, 2),
(3, 5, 4, TIMESTAMP '2022-10-11 11:00:00', TIMESTAMP '2022-10-15 11:00:00', 1, 3);

INSERT INTO "PUBLIC"."WATER_QUANTITY" VALUES
(1, 23.00, 1),
(2, 23.00, 1),
(3, 23.00, 1);

INSERT INTO "PUBLIC"."SENSOR_ALERT_STATUS" VALUES
(1, 00, NULL, TIMESTAMP '2022-10-11 11:00:00', 3);