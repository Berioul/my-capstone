
// @ts-ignore
import mapboxgl from '!mapbox-gl'; // eslint-disable-line import/no-webpack-loader-syntax
import {useEffect, useRef} from "react";
import "./Map.css"
import {RulerControl} from "mapbox-gl-controls";
import'mapbox-gl/dist/mapbox-gl.css'


mapboxgl.accessToken =
    'pk.eyJ1IjoiYmVyaW91bCIsImEiOiJjbDFrazE4ZDAwMHRjM2NvMmxnZGFjYnBnIn0.7l4A-hGFQekIZ6SseStohw';


export default function Map() {

    const mapContainer = useRef( null);
    const map = useRef(null as mapboxgl.Map);
    const lng =5.36978;
    const lat =43.296482;
    const zoom  = 6.5;




    useEffect(() => {
        if (map.current) return; // initialize map only once
        map.current = new mapboxgl.Map({
            container: mapContainer.current,
            style: 'mapbox://styles/mapbox/streets-v11',
            center: [lng, lat],
            zoom: zoom,

        });
        map.current.addControl(new RulerControl({
            units:'nauticalmiles',
            mainColor:'blue',
            labelFormat: n => `${n.toFixed(2)} ml`
        }));


        map.current.addControl(new mapboxgl.NavigationControl());

        });

    return (
        <div>
            <div ref={mapContainer} className="map-container"/>
        </div>
    );

}