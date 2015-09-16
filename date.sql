/* this works */
select CONFIRMATION_NUMBER from reservations;  
/* this works */
select date('now'); 
/*   this fails */
update reservations set DATE_OF_RESERVATION = date('now') where CONFIRMATION_NUMBER = 275130;
select CONFIRMATION_NUMBER, DATE_OF_RESERVATION from reservations where CONFIRMATION_NUMBER = 275130;
/*   this fails */
update reservations set DATE_OF_RESERVATION = date('2015-09-13') where CONFIRMATION_NUMBER = 275130;
select CONFIRMATION_NUMBER, DATE_OF_RESERVATION from reservations where CONFIRMATION_NUMBER = 275130;
/*   this fails */
update reservations set DATE_OF_RESERVATION = date(2015-09-13) where CONFIRMATION_NUMBER = 275130;
select CONFIRMATION_NUMBER, DATE_OF_RESERVATION from reservations where CONFIRMATION_NUMBER = 275130;
/* this fails  by just giving wrong date */
update reservations set DATE_OF_RESERVATION = 2015-09-13 where CONFIRMATION_NUMBER = 275130;
select CONFIRMATION_NUMBER, DATE_OF_RESERVATION from reservations where CONFIRMATION_NUMBER = 275130;
/*   this fails */
update reservations set DATE_OF_RESERVATION = '2015-09-13' where CONFIRMATION_NUMBER = 275130;
select CONFIRMATION_NUMBER, DATE_OF_RESERVATION from reservations where CONFIRMATION_NUMBER = 275130;

update reservations set DATE_OF_RESERVATION = "2015-09-13" where CONFIRMATION_NUMBER = 275130;
select CONFIRMATION_NUMBER, DATE_OF_RESERVATION from reservations where CONFIRMATION_NUMBER = 275130;

update reservations set DATE_OF_RESERVATION = "2015-09-13 00:00:00.000" where CONFIRMATION_NUMBER = 275130;
select CONFIRMATION_NUMBER, DATE_OF_RESERVATION from reservations where CONFIRMATION_NUMBER = 275130;

