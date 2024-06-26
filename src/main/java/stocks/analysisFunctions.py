#!/usr/bin/env python
# coding: utf-8

# ## Functions to analyse data

# In[ ]:


import pandas as pd
import numpy as np
import matplotlib.pyplot as plt


# ### Moving averages (libisevad keskmised)

# In[25]:


"""
Function to calculate simple moving average based on averages of dates
_average_price - date average price

Returns moving average of given array

Suhtuda skeptiliselt, sest jättes välja suuri ühe päeva väärtusi, siis
võib kaasa tuua näiliseid hüppeid.

Libisev keskmine peaks olmea pool valitseva turutsükli pikkusest
"""
def simple_moving_average(_price, window=10):
    average = np.zeros(len(_price))
    for i in range(window-1, len(_price)):
        average[i]=np.mean(_price[(i-window+1):i+1])
    return average

"""
Function to calculate exponential moving average

_average_price - date average price

Returns expontential moving average of given array

Parem kui lihtne libisev keskmine, annab värskematele andmetele suurema kaalu ja ei luba
suurt hüpet kui vanad andmed peaks välja libisema

200-päeva libisev keskmine sobib pikaajalisetele investoritele, kes tahavad saada kasu pikkadest trendidest
Enamik kauplejaid võib kasutada 10-30 päevast libisevate keskmist

Reegleid:

Kui keskmine tõuseb, võta pikk positsioon, osta kui hind jõuab libiseva keskmise juurde. Kui võtnud positsiooni, siis astea stopp 
viimasest väikesest põhjast allapoole ning liiguta nullkahjumini, kui hind on kõrgemal sulgunud.

Aeglane keskmine aitab trendi tuvastada, kiire libisev paneb paika väärtustsiooni piiri, kui hakkate aktsiat ostma, siis mitte osta
väärtustsoonist kallimalt
"""

def exponential_moving_average(_price, window=10):
    factor = (2.0/(window+1.0))
    average = np.zeros(len(_price))
    #Estimate first possible point as simple moving average
    average[window-1] = np.mean(_price[:window])
    for i in range(window, len(_price)):
        average[i]=_price[i]*factor+average[i-1]*(1.0-factor)
    return average


"""
moving average convergence-divergence(MACD)

_average_price - date average price

Returns three two lines - MACD line and signal line

MACD indikaator koosneb kahest joonest: MACD joon ja signaaljoon
MACD joon tuleneb kahest eksponentsiaalselst libisevast keskmisest
Singaaljoon tasandab MACD-joont veel ühe libiseva keskmisega
Ostusignaaliks loetakse kiire MACD-joone lõikumist aeglase signaaljoonega alt üles,
müügisignaaliks loetakse kiire joone lõikumist aeglasega ülalt alla

difference - MACD-histogramm. Tõusev MACD-historgarmm näitab, et pullid muutuvad tugevamaks
MACD-historgrammis, ostke siis kui histogramm enam ei lange ja võtab suuna üles
"""

def estimate_macd(_price):
    ema12 = exponential_moving_average(_price, window=12)
    ema26 = exponential_moving_average(_price, window=26)
    macd_line = ema12-ema26
    signal_line = exponential_moving_average(macd_line, window=9)
    difference = macd_line-signal_line
    return [macd_line, signal_line, difference]


# ## Suunasüsteem

# Suunatud liikumine on see osa tänasest kauplemisvahemikust, mis jääb eelmise päeva kauplemisvahemikust välja.
# 
# Kui tänane kauplemisvahemik on kõrgemal, siis on suunatud liikumine positiivne (+DM)
# Kui madalamal, siis liikumine on negatiivne (-DM)
# Kui kauplemisvahemik on jääb eilse vahemiku sisse või ulatub sellest võrdselt kõrgemale-madalamale, siis liikumist ei ole (DM=0)
# 
# Vaata kuskilt mujalt need arvutused üle!!

# In[142]:


"""
Estimate directional movement using stock daily high and low prices

Returns directional movement values

"""

def estimate_directional_movement(_day_high, _day_low):
    DMs = np.zeros(len(_day_high))
    for i in range(1, len(_day_high)):
        #Estimate if todays high is larger than yesterdays
        DM_pos = _day_high[i]-_day_high[i-1]
        if DM_pos<0:
            DM_pos = 0
        #Estimate if todays low is smaller than yesterdays
        DM_neg = _day_low[i]-_day_low[i-1]
        if DM_neg>0:
            DM_neg = 0
        DMs[i] = DM_pos+DM_neg
"""
Estimates true range using daily high and low
"""
def estimate_true_range(_day_high, _day_low):
    return _day_high-_day_low

"""
Estimate directional indicator, allows estimating the relative change and smoothing it using moving average
"""
def estimate_directional_indicator(_DMs, _TRs):
    return simple_moving_average(_DMs/_TRs, window=13)


# ## Ostsillaatorid

# MACD ja suunasüsteemiga võrreldes, mis tuvastavad trende, vaatlevad ostillaatorid pöördepunkte. OStsillaatorid tuvastavad turu jätkusuutmatut pessimisi ja optimismi.
# 
# Ülemüümise ja üleostmise jooned tuleks tõmmata kuskil viimase kuue kuu kõrgeimate tippude ja sügavaimate põhjade tasemel. Ideaalis ostsillaator peaks jääma kogu vaadeldava perioodi jooksul ainult umbes viie protsendi jooksul kummagi joone taha.
# Stohhastiline ostsillaator kõigub 0% ja 100% vahel, ülemüümise ja üleostmise jooned tõmmatakse 20% ja 80% tasemele, millest alla- ja üle-poole jääb üleostetud ja ülemüüdud ala

# In[ ]:


def estimate_stochastic_oscillator(_price, window=5):
    raw_oscillator = np.zeros(len(_price))
    smooth_oscillator = np.zeros(len(_price))
    for i in range(window-1, len(_price)):
        raw_oscillator[i]= 100*(_price[i]-np.min(_price[(i-window+1):i+1]))/(np.max(_price[(i-window+1):i+1])-np.min(_price[(i-window+1):i+1]))
        smooth_oscillator[i] = 100*(np.sum(_price[i-window:i+1]-np.min(_price[(i-window+1):i+1]))/(window*(np.max(_price[(i-window+1):i+1])-np.min(_price[(i-window+1):i+1]))))
    return raw_oscillator, smooth_oscillator
                        


# In[ ]:


df = pd.read_csv("FSKRS-2022-07-19-2023-07-19.csv", decimal=",", sep=";", skiprows=1, parse_dates=['Date'])


# In[26]:


print(estimate_macd(df["Average price"].values))


# In[ ]:


print(simple_moving_average(df["Average price"].values)[:20])
print(exponential_moving_average(df["Average price"].values)[:20])
print(df["Average price"].values[:20])


# In[17]:


plt.plot(simple_moving_average(df["Average price"].values), "--")
plt.plot(exponential_moving_average(df["Average price"].values))
plt.plot(df["Average price"].values, color="k")
plt.ylim(14,20)
plt.savefig('books_read.png')


# In[141]:


macd, signal, dif = estimate_macd(df["Average price"].values)
plt.plot(macd[100:])
plt.plot(signal[100:], "--")
#plt.xlim(150, 200)
#plt.ylim(-0.005,0.005)


# In[21]:


df.groupby([df['Date'].dt.year, df['Date'].dt.month]).mean()


# In[ ]:




