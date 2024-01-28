/**
 * @license
 * Copyright 2019 Google LLC. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */


// This example creates circles on the map, representing populations in North
// America.

// First, create an object containing LatLng and population for each city.

interface City {
  center: google.maps.LatLngLiteral;
  radius: number;
  opacity: number;
}

const citymap: Record<string, City> = {
  Council_District1: {
    center: { lat:40.47204338 , lng:  -80.0114356 },
    radius: 0.0452*1000,
    opacity: .1
  },
  Council_District2: {
    center: { lat:40.40387053 , lng:  -80.00166325 },
    radius: 0.105*1000,
    opacity: .15
  },
  Council_District3: {
    center: { lat:40.42395678 , lng: -79.98048644 },
    radius: 0.03555*1000,
    opacity: .25
  },
  Council_District4: {
    center: { lat:40.40033096 , lng:  -80.00393496 },
    radius: .034*1000,
    opacity: .35
  },
  Council_District5: {
    center: { lat:40.40297258 , lng: -79.93087371 },
    radius: .0524*1000,
    opacity: 100
    
  },
  Council_District6: {
    center: { lat:40.45434407 , lng: -79.99245957 },
    radius: .0524*1000,
    opacity: .4
  },
  Council_District7: {
    center: { lat:40.46662354 , lng: -79.95112246 },
    radius: 0.02653*1000,
    opacity: .2
  },
  Council_District8: {
    center: { lat:40.44937905 , lng: -79.93436589 },
    radius: .0524*1000,
    opacity: .3
  },
  Council_District9: {
    center: { lat:40.46428654 , lng: -79.90640247 },
    radius: 0.0474*1000,
    opacity: .5
  },
  
};

function initMap(): void {
  // Create the map.
  const map = new google.maps.Map(
    document.getElementById("map") as HTMLElement,
    {
      zoom: 13,
      center: { lat: 40.44519495113776,  lng: -80.00975413452268, },
      // mapTypeId: "terrain",
    }
  );

  // Construct the circle for each value in citymap.
  // Note: We scale the area of the circle based on the population.
  for (const city in citymap) {
    // Add the circle for this city to the map.
    const cityCircle = new google.maps.Circle({
      strokeColor: "#FF0000",
      strokeOpacity: 0.8,
      strokeWeight: 2,
      fillColor: "#FF0000",
      fillOpacity: citymap[city].opacity,
      map,
      center: citymap[city].center,
      radius: Math.sqrt(citymap[city].radius) * 100,
    });
  }
}

declare global {
  interface Window {
    initMap: () => void;
  }
}
window.initMap = initMap;
export {};
