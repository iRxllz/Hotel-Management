create database hotel_reservation_system;

use hotel_reservation_system;

insert into hotels (hotel_id, name, latitude, longitude) values
(1, 'Hotel Ramada', 46.764654252624204, 23.598674125224626),
(2, 'Grand Hotel Italia', 46.7522792440665, 23.605990381045697),
(3, 'Hampton by Hilton', 46.77539900854998, 23.60182699638966);

insert into rooms (room_number, type, price, is_available, hotel_id) values
(210, 2, 200, true, 1),
(125, 1, 350, true, 1),
(87, 1, 300, false, 1),
(41, 3, 240, true, 2),
(32, 2, 410, false, 3),
(21, 2, 350, true, 3),
(64, 3, 300, true, 3);

select * from hotels;

select * from rooms;