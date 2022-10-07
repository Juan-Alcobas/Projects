import requests
from bs4 import BeautifulSoup
import pandas
#primary url to work on web scraping
#https://pythonizing.github.io/data/real-estate/rock-springs-wy/LCWYROCKSPRINGS/
r=requests.get("https://pythonizing.github.io/data/real-estate/rock-springs-wy/LCWYROCKSPRINGS/")

#stores the html of the webpage
c=r.content

#makes html into a viewable format, like using the inspect tool on a webpage
soup=BeautifulSoup(c,"html.parser")

#finds and stores the html data that will be scraped
all=soup.find_all("div",{"class":"propertyRow"})

#gets the last value of the dictionary with the below parameters, being 3
pageNumber=soup.find_all("a",{"class":"Page"})[-1].text
#print(pageNumber)

#list to store the dictionary after each iteration
l=[]

#uses a modified url which can allow for web crawling
baseURL = "http://pythonizing.github.io/data/real-estate/rock-springs-wy/LCWYROCKSPRINGS/t=0&s="

#loops through the web pages
for page in range(0,int(pageNumber)*10,10):
    #print(baseURL+str(page)+".html")
    r=requests.get(baseURL+str(page)+".html")
    c=r.content
    soup=BeautifulSoup(c,"html.parser")
    all=soup.find_all("div",{"class":"propertyRow"})
    for item in all:
        #dictionary that will be used for transfer to a list
        d={}
        d["Price"]=item.find("h4",{"class":"propPrice"}).text.replace("\n","").replace(" ","")
        d["Address"]=item.find_all("span",{"class":"propAddressCollapse"})[0].text
        d["Locality"]=item.find_all("span",{"class":"propAddressCollapse"})[1].text

        try:
            d["Beds"]=item.find("span",{"class","infoBed"}).find("b").text
        except:
            d["Beds"]=None
        try:
            d["Area"]=item.find("span",{"class","infoSqFt"}).find("b").text
        except:
            d["Area"]=None
        try:
            d["Full Baths"]=item.find("span",{"class","infoValueFullBath"}).find("b").text
        except:
            d["Full Baths"]=None
        try:
            d["Half Baths"]=item.find("span",{"class","infoValueHalfBath"}).find("b").text
        except:
            d["Half Baths"]=None

        for column_group in item.find_all("div",{"class":"columnGroup"}):
            #allows for two elements to be used in the for loop comparisons
            for feature_group, feature_name in zip(column_group.find_all("span",{"class":"featureGroup"}),column_group.find_all("span",{"class":"featureName"})):
                if "Lot Size" in feature_group.text:
                    d["Lot Size"]=feature_name.text
        #appends the dictionaries keys and values to the list
        l.append(d)

#creates a dataframe using the panda library
df=pandas.DataFrame(l)

#shows the visuals of the web scraping and how the output data should look
print(df)

#writes the data to csv file so it can be viewed in your folder
df.to_csv("Output.csv")


