from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import re

driver = webdriver.Firefox()
driver.get( "https://www.myclubwyndham.com/ffr/index.do" )

userNameElement = driver.find_element_by_name( "userNamelabel" )
userNameElement.send_keys( "CarolynBenscoter" )

passwordElement = driver.find_element_by_name( "passwordlabel" )
passwordElement.send_keys( "sunnyboy1" )
passwordElement.submit()

driver.get( "https://www.myclubwyndham.com/ffr/secure/member/reservation_summary/reservationSummary.do" )
webpageSrc = driver.page_source

webpageSrc = webpageSrc.encode('utf-8')

currentReservation = re.split("name=\"selectedConfirmation\"",webpageSrc)[1]

confirmationNumber = re.split(":true\'>",currentReservation)[1]
confirmationNumber = re.search("[0-9][0-9][0-9][0-9][0-9][0-9]",confirmationNumber).group(0)

checkInDate = re.search("[0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9]",currentReservation).group(0)

numNights = re.split(checkInDate,webpageSrc)[1]
numNights = re.search("[0-9]+",numNights).group(0)

resortName = re.split("reservationConfirm_ResortName",currentReservation)[1]
resortName = re.search(".+..td",resortName).group(0)
resortName = resortName.replace("</td","")
resortName = resortName.replace("\">","")
resortName = resortName

unitType = re.split(resortName,currentReservation)[1]
unitType = re.split("<td>",unitType)[1]
unitType = re.split("</td>",unitType)[0]



print( "confirmation number: " + str(confirmationNumber) )
print( "checkInDate: " + str(checkInDate) )
print( "number of nights: " + str(numNights) )
print( "resort name: " + str(resortName) )
print( "unit type: " + str(unitType) )
