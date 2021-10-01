/* import { initializeApp } from "firebase/app";
import firebase from 'firebase/compat/app';
import 'firebase/auth'
import 'firebase/firestore'


const firebaseConfig = {
    apiKey: "AIzaSyA9FTHgoXEmVgVI_ZdgSnJfiyjmcqXjmK8",
    authDomain: "tyz-recipes.firebaseapp.com",
    projectId: "tyz-recipes",
    storageBucket: "tyz-recipes.appspot.com",
    messagingSenderId: "349684669609",
    appId: "1:349684669609:web:c9c75e325226a0697e1db8"
  };
  
  // Initialize Firebase
  const app = initializeApp(firebaseConfig)

  export const auth = firebase.auth()
  export const db = firebase.firestore()

  export default firebase */

  
import { initializeApp } from "firebase/app"
import { getFirestore } from "firebase/firestore"

const firebaseApp = initializeApp({
    apiKey: "AIzaSyA9FTHgoXEmVgVI_ZdgSnJfiyjmcqXjmK8",
    authDomain: "tyz-recipes.firebaseapp.com",
    projectId: "tyz-recipes",
    storageBucket: "tyz-recipes.appspot.com",
    messagingSenderId: "349684669609",
    appId: "1:349684669609:web:c9c75e325226a0697e1db8"
  });

  const db = getFirestore();

  export { db };