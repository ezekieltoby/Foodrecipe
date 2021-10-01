import React, { useState } from 'react';
import "../app.css";
import {db} from '../firebasenode';

const Foodinsert = () => {
const [name, setName] = useState("");
const [country, setCountry] = useState("");
const [timer, setTimer] = useState("");
const [ingredients, setIngredients] = useState("");

const [loader, setLoader] = useState(false);

const handleSubmit = (e) => {
    e.preventDefault();
    setLoader(true)

    db.collection('foodie').add({
        name: name,
        country: country,
        timer: timer,
        ingredients: ingredients,


    })
    .then(()=> {
        alert('Recipe has been submitted ')
        setLoader(false);
    })
    .catch((error) => {
        alert(error.message);
        setLoader(false);
    });

    setName("");
    setCountry("");
    setTimer("");
    setIngredients("");
};

    return (
        <form className="form" onSubmit = 
        {handleSubmit}>
            <h1>Insert Food </h1>

            <label>Name</label>
            <input placeholder = "name" 
            value = {name}
            onChange={(e)=> setName(e.target.
            value)}
            > 
            </input>
            
            <label>Country</label>
            <input placeholder = "Country"
            value = {country}
            onChange={(e)=> setCountry(e.target.
            value)}
            ></input>

            <label>Timer</label>
            <input placeholder = "timer"
            value = {timer}
            onChange={(e)=> setTimer(e.target.
            value)}
            ></input>

            <label>Ingredients</label>
            <textarea placeholder = "ingredients"
            value = {ingredients}
            onChange={(e)=> setIngredients(e.target.
            value)}
            >
            </textarea>



            <button type="submit" 
            style = {{background : loader ? "#ccc" : 
            "rgb(2, 2, 110)"}}
            >
                Submit
                </button>


            
        </form>
    );
};

export default Foodinsert;
