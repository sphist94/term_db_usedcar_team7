--Q1

select count(*) 
from Account 
where Account_type=1 
or Account_type=2;

--result: 279

--Q2
select Lname, Fname, Address 
from Account 
where Account_type=3;

--Q3 
select count(*) 
from ( 
select count(*) 
from orders 
group by(orders.Cuid) 
having count(*) > 1
);

--result: 83

--Q4
select count(V.Poid) 
from Vehicle V, Orders O, Maker M 
where M.National_no=2 
and M.Maname = V.Maname 
and (O.Sold_date between '2018-01-01' and '2018-12-31') 
and V.Poid=O.Poid;

--result: 53

--Q5
select count(distinct M.Moname) 
from Orders O, Model M, Vehicle V 
where M.Moname=V.Moname 
and V.Poid=O.Poid 
and (O.Sold_date between '2017-10-02' and '2018-10-02');

--result: 31

--Q6
select sum(V.Price) 
from Vehicle V, Orders O 
where V.Poid=O.Poid 
and O.Sold_date between '2019-04-02' and '2019-10-02';

--result: 317,400

--Q7
select count(V.Veid) 
from Vehicle V, Orders O 
where V.Poid=O.Poid 
and V.Price<=1500;

--result: 36

--Q8
select Max(Price) 
from Vehicle, Colored, Color_type 
where Vehicle.Poid = Colored.Poid 
and Colored.Coid = Color_type.Coid 
and Color_type.Coname = 'black' 
or Color_type.Coname = 'white';

--result: 10,000

--Q9
select count(V.Poid) 
from Vehicle V, Orders O 
where V.Trname='manual' 
and V.Enname>=2000 
and V.Poid = O.Poid;

--result: 62

--Q10
select min(Account.Birth_date) 
from Account, Customer 
where Customer.Cuid NOT IN(
select Orders.Cuid 
from Orders) 
and Customer.Cuid = Account.Id;

--result: 1956-01-08


--Q11
select count(*) 
from Account, Customer 
where Customer.Cuid in (
select Orders.Cuid 
from orders 
group by(orders.Cuid) 
having count(*) = 1) 
and Account.Id = Customer.Cuid 
and Account.Occupation is not null;

--result: 32

--Q12
select avg(V.Enname) 
from Vehicle V, Fueled F, Fuel_type Y 
where Y.Funame!='LPG' and F.Poid=V.Poid and F.Fuid=Y.Fuid and Y.Fuid in 
(select count(Y.Fuid) 
from Vehicle V, Fueled F, Fuel_type Y 
where F.Poid=V.Poid and F.Fuid=Y.Fuid 
Group by F.Poid 
having count(Y.Fuid)=2);

--result: 1954.83871

--Q13

select count(*)
from(
select Maname
from(
select vehicle.Poid
from vehicle, colored
where vehicle.Poid = colored.Poid
group by vehicle.Poid
having count(*) > 1) two_tones, Vehicle,  fueled, fuel_type
where two_tones.Poid = vehicle.Poid
and Vehicle.Poid = fueled.Poid
and Vehicle.Caname <> 'SUV'
and fueled.Fuid = fuel_type.Fuid
and fuel_type.Funame <> 'diesel'
group by Vehicle.Maname
having count(*) > 1);

--result: 8

--Q14

--query start
SET SERVEROUTPUT ON
declare 
min_date varchar(15);
corresponsive_n INT;

begin
select substr(max(Account.Birth_date), 0, 7)
into min_date 
from Account, Customer, Orders, Vehicle 
where Vehicle.Price >= 3000 
and Vehicle.Poid = Orders.Poid 
and Orders.Cuid = Customer.Cuid 
and Account.Id = Customer.Cuid;

select count(*)
into corresponsive_n 
from Orders, Vehicle
where Orders.Poid = Vehicle.Poid
and Vehicle.Age = min_date;

DBMS_OUTPUT.PUT_LINE('The youngest customer for conditions');
DBMS_OUTPUT.PUT_LINE('------------------------------------------');
DBMS_OUTPUT.PUT_LINE(min_date);
DBMS_OUTPUT.PUT_LINE('Total Number for conditions');
DBMS_OUTPUT.PUT_LINE('-------------------------------');
DBMS_OUTPUT.PUT_LINE(corresponsive_n);
end;
/
--query end


--result: 3

--Q15  

select min(vehicle.Veid) keep(dense_rank first order by Mileage)
from vehicle, colored, color_type
where vehicle.Maname = (
select max(Maname) keep(dense_rank first order by sum(Price) desc)
from vehicle, orders
where vehicle.Poid = orders.Poid
group by vehicle.Maname)
and vehicle.Poid = colored.Poid
and colored.Coid = color_type.Coid
and color_type.Coname <> 'black';


--Q16
--query start
set serveroutput on
declare
highest_model varchar(15);
phone_number varchar(15);

begin
select max(Moname) keep(dense_rank first order by tot_price desc)
into highest_model
from (select vehicle.Moname, sum(vehicle.Price) as tot_price from vehicle group by vehicle.Moname) b;

select max(Phone_no) keep(dense_rank first order by length(Address) desc)
into phone_number
from (
select account.Id, account.Phone_no, account.Address
from account, customer, vehicle, orders
where vehicle.Moname = highest_model
and vehicle.Poid = orders.Poid
and orders.Cuid = customer.Cuid
and customer.Cuid = account.Id);

DBMS_OUTPUT.PUT_LINE('The customer phone number of longest address  ');
DBMS_OUTPUT.PUT_LINE('-------------------------------------------------------');
DBMS_OUTPUT.PUT_LINE(phone_number);

end;
/
--query end


--Q17
--query start
set serveroutput on
declare
third_maker maker.Maname%type;
oldest_vehicle vehicle.Poid%type;
owner_gender account.Gender%type;
different_gender_count INT;

begin
select Maname
into third_maker
from(
select Maname, cnt, row_number() over(order by cnt desc) row_id
from ( 
select Maname, count(*) as cnt
from vehicle 
group by Maname )
)
where row_id = 3;
DBMS_OUTPUT.PUT_LINE(third_maker);

select max(vehicle.Poid) keep(dense_rank first order by Age)
into oldest_vehicle
from vehicle, orders
where vehicle.Maname = third_maker
and orders.Poid = vehicle.Poid;
DBMS_OUTPUT.PUT_LINE(oldest_vehicle);

select account.Gender
into owner_gender
from orders, account, customer
where orders.Poid = oldest_vehicle
and customer.Cuid = orders.Cuid
and customer.Cuid = account.Id;

DBMS_OUTPUT.PUT_LINE(owner_gender);

select count(*)
into different_gender_count
from account
where account.Gender <> owner_gender;
DBMS_OUTPUT.PUT_LINE(different_gender_count);
end;
/
--query end


--Q18

--query start
select avg(Price)
from(
select row_number() over(order by Mileage) row_id, Price, Mileage
from (
  select * 
  from (
    select * 
    from vehicle 
    where (vehicle.Enname < '2000' or vehicle.Enname > '3000')
    and price < 5000) v, colored, color_type
  where colored.Poid = v.Poid
  and colored.Coid = color_type.Coid
  and (color_type.Coname = 'black' or color_type.Coname = 'gray')) b)
where row_id < 6;
--query end

--result 2740

--Q19
--query start
select avg(Mileage)
from vehicle, orders
where vehicle.Maname = (select Min(Maname) keep(dense_rank first order by count(*))
from orders, (
select *
from vehicle natural join maker
                 natural join fueled
                 natural join fuel_type
where Funame='gasoline'
and National_no = 2) t
where t.Poid = orders.Poid
group by(t.Maname))
and vehicle.Poid = orders.Poid;

--result: 174968.75
--query end

--Q20
--query start
set serveroutput on
declare
favorite_maker_by_female maker.Maname%type;
favorite_color_by_male color_type.Coid%type;
least_maker maker.Maname%type;
avrage_price vehicle.Price%type;
detailed_info_vehicle vehicle%rowtype;


most_same_poid  INTEGER := 0;
comp_price INTEGER := 0;
difference INTEGER := 11111111111;

begin
select max(Maname) keep(dense_rank first order by count(*) desc)
into favorite_maker_by_female
from orders, account, vehicle
where orders.Cuid = account.Id
and account.Gender = 'F'
and orders.Poid = vehicle.Poid
group by Maname;

select max(Coid) keep(dense_rank first order by count(*) desc)
into favorite_color_by_male
from vehicle, orders, colored
where vehicle.Maname = favorite_maker_by_female
and vehicle.Poid = orders.Poid
and colored.Poid = vehicle.Poid
group by Coid;

select maker.Maname
into least_maker
from maker, model
where Moname = (
select min(Moname) keep(dense_rank first order by count(*))
from vehicle, colored
where colored.Coid = favorite_color_by_male
and colored.Poid = vehicle.Poid
group by Moname)
and maker.Maname = model.Maname;

select avg(Price)
into avrage_price 
from vehicle, orders
where vehicle.Maname = least_maker
and vehicle.Poid <> orders.Poid;

FOR row in (select vehicle.Poid, Price from vehicle, orders where vehicle.Maname = least_maker and vehicle.Poid <> orders.Poid) loop
if abs(comp_price - row.Price) < difference then
difference := abs(comp_price - row.Price);
comp_price := row.Price;
most_same_poid := row.Poid;
end if;
end loop;

DBMS_OUTPUT.PUT_LINE(most_same_poid);

select *
into detailed_info_vehicle
from vehicle
where vehicle.Poid = most_same_poid;

DBMS_OUTPUT.PUT_LINE('VID                  Dealer_id   Price  Mileage            ');
DBMS_OUTPUT.PUT_LINE(detailed_info_vehicle.Veid||'   '||detailed_info_vehicle.Deid||'    '||detailed_info_vehicle.Price||'     '||detailed_info_vehicle.Mileage);

end;
/
