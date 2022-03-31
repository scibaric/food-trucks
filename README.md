# Food Trucks API

Food Trucks API is REST API for finding food trucks in San Francisco. For simple use
and testing purposes H2 in-memory database is used, in a real production environment
consider using some kind of real database. API starts on default port 8080.

Data is loaded in DB with migration sql script. Script is filled with data from [link](https://data.sfgov.org/Economy-and-Community/Mobile-Food-Facility-Permit/rqzj-sfat/data).

Columns `Approved` and `ExpirationDate` are converted to String data type but if business
logic requires date manipulation then it would be better to use LocalDateTime.

In future DTOs and mappers could be introduced but for now there is no need because it increases 
complexity. Food Truck entity is serialized to JSON as-is.

Endpoint `close-to-me` requires user coordinates and then calculates and finds closest food trucks.
Approximately distance of one block in latitude would be ±0.002 and in longitude ±0.004.
Some sophisticated math function could be better but this is good enough.

## Test, build and run
Maven automation tool is used for testing the API and building .jar file.
To test, build and run user should be positioned inside top level project directory.
For test and build use command `./mvnw verify`.

All unit and integration tests should pass and .jar file is generated in `./target` directory
which should appear inside project upon completion of the last command. Jar can be started
with command `java -jar ./target/food-trucks-0.0.1-SNAPSHOT.jar` and API should be available on localhost
and port 8080, testing URLs are:
 - http://localhost:8080/api/v1/food-trucks - Returns all
 
  ```
  [
    {
        "locationId": 364218,
        "applicant": "The Chai Cart",
        "facilityType": "Push Cart",
        "cnn": 9543000,
        "locationDescription": "NEW MONTGOMERY ST: AMBROSE BIERCE ST to MISSION ST (77 - 99)",
        "address": "79 NEW MONTGOMERY ST",
        "blockLot": "3707014",
        "block": "3707",
        "lot": "014",
        "permit": "12MFF-0083",
        "status": "SUSPEND",
        "foodItems": "Hot Indian Chai (Tea)",
        "x": 6012504.31,
        "y": 2114926.77,
        "latitude": "37.78788969990609",
        "longitude": "-122.40053532677749",
        "schedule": "http://bsm.sfdpw.org/PermitsTracker/reports/report.aspx?title=schedule&report=rptSchedule&params=permit=12MFF-0083&ExportPDF=1&Filename=12MFF-0083_schedule.pdf",
        "daysHours": "Mo-Su:7AM-6PM",
        "received": "20120403",
        "priorPermit": false,
        "location": "(37.78788969990609, -122.40053532677749)",
        "firePreventionDistricts": 12,
        "policeDistricts": 2,
        "supervisorDistricts": 9,
        "zipCodes": 28855,
        "neighborhoodsOld": 6
    },
    {
        "locationId": 453012,
        "applicant": "Tacos Santana",
        "cnn": 3742000,
        "locationDescription": "CARROLL AVE: QUINT ST to BAY SHORE BLVD \\ THORNTON AVE (2100 - 2199)",
        "address": "2101 CARROLL AVE",
        "blockLot": "5436012",
        "block": "5436",
        "lot": "012",
        "permit": "13MFF-0112",
        "status": "EXPIRED",
        "x": 6011371.49,
        "y": 2093947.37,
        "latitude": "37.73022168130492",
        "longitude": "-122.40297852668597",
        "schedule": "http://bsm.sfdpw.org/PermitsTracker/reports/report.aspx?title=schedule&report=rptSchedule&params=permit=13MFF-0112&ExportPDF=1&Filename=13MFF-0112_schedule.pdf",
        "daysHours": "Mo-Su:10AM-9PM",
        "noiSent": "07/25/2013 12:00:00 AM",
        "approved": "02/07/2018 12:00:00 AM",
        "received": "20130605",
        "priorPermit": false,
        "expirationDate": "07/15/2018 12:00:00 AM",
        "location": "(37.73022168130492, -122.40297852668597)",
        "firePreventionDistricts": 10,
        "policeDistricts": 3,
        "supervisorDistricts": 8,
        "zipCodes": 58,
        "neighborhoodsOld": 1
    },
    ...
 ]   
 ```
 
  - http://localhost:8080/api/v1/food-trucks/1571753 - Returns by locationId
  
  ```
  {
    "locationId": 1571753,
    "applicant": "The Geez Freeze",
    "facilityType": "Truck",
    "cnn": 887000,
    "locationDescription": "18TH ST: DOLORES ST to CHURCH ST (3700 - 3799)",
    "address": "3750 18TH ST",
    "blockLot": "3579006",
    "block": "3579",
    "lot": "006",
    "permit": "21MFF-00015",
    "status": "APPROVED",
    "foodItems": "Snow Cones: Soft Serve Ice Cream & Frozen Virgin Daiquiris",
    "x": 6004575.87,
    "y": 2105666.97,
    "latitude": "37.76201920035647",
    "longitude": "-122.42730642251331",
    "schedule": "http://bsm.sfdpw.org/PermitsTracker/reports/report.aspx?title=schedule&report=rptSchedule&params=permit=21MFF-00015&ExportPDF=1&Filename=21MFF-00015_schedule.pdf",
    "approved": "01/28/2022 12:00:00 AM",
    "received": "20210315",
    "priorPermit": false,
    "expirationDate": "11/15/2022 12:00:00 AM",
    "location": "(37.76201920035647, -122.42730642251331)",
    "firePreventionDistricts": 8,
    "policeDistricts": 4,
    "supervisorDistricts": 5,
    "zipCodes": 28862,
    "neighborhoodsOld": 3
  }
  ```
  
  - http://localhost:8080/api/v1/food-trucks/close-to-me?latitude=37.791&longitude=-122.401 - Returns all
  food trucks close to coordinates which are provided by user.
  
  ```
  [
    {
        "locationId": 735315,
        "applicant": "Ziaurehman Amini",
        "facilityType": "Push Cart",
        "cnn": 4969000,
        "locationDescription": "DRUMM ST: MARKET ST to CALIFORNIA ST (1 - 6)",
        "address": "1 CALIFORNIA ST",
        "blockLot": "0264004",
        "block": "0264",
        "lot": "004",
        "permit": "15MFF-0159",
        "status": "REQUESTED",
        "x": 6013552.57,
        "y": 2116844.50,
        "latitude": "37.793213731663414",
        "longitude": "-122.39704303671823",
        "schedule": "http://bsm.sfdpw.org/PermitsTracker/reports/report.aspx?title=schedule&report=rptSchedule&params=permit=15MFF-0159&ExportPDF=1&Filename=15MFF-0159_schedule.pdf",
        "received": "20151231",
        "priorPermit": false,
        "expirationDate": "03/15/2016 12:00:00 AM",
        "location": "(37.793213731663414, -122.39704303671823)",
        "firePreventionDistricts": 4,
        "policeDistricts": 1,
        "supervisorDistricts": 10,
        "zipCodes": 28860,
        "neighborhoodsOld": 6
    },
    {
        "locationId": 812017,
        "applicant": "SF Street Food",
        "facilityType": "Truck",
        "cnn": 12320000,
        "locationDescription": "SUTTER ST: CLAUDE LN to GRANT AVE (216 - 299)",
        "address": "290 SUTTER ST",
        "blockLot": "0287014",
        "block": "0287",
        "lot": "014",
        "permit": "16MFF-0126",
        "status": "REQUESTED",
        "foodItems": "Chicken Tikka Masala: Vegetarian Rice Plate: Burritos: Tacos: Mango Lassi",
        "x": 6011162.14,
        "y": 2115691.27,
        "latitude": "37.78991386713444",
        "longitude": "-122.40523289252366",
        "schedule": "http://bsm.sfdpw.org/PermitsTracker/reports/report.aspx?title=schedule&report=rptSchedule&params=permit=16MFF-0126&ExportPDF=1&Filename=16MFF-0126_schedule.pdf",
        "received": "20160531",
        "priorPermit": false,
        "location": "(37.78991386713444, -122.40523289252366)",
        "firePreventionDistricts": 5,
        "policeDistricts": 1,
        "supervisorDistricts": 10,
        "zipCodes": 28857,
        "neighborhoodsOld": 6
    },
    ...
  ]
  ```