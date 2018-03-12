import * as firebase from 'firebase';

const config = {
  apiKey           : 'AIzaSyAySCUf7z75fDoPZrE-wgkjhIUxYv_mGt8',
  authDomain       : 'sausage-cb48b.firebaseapp.com',
  databaseURL      : 'https://sausage-cb48b.firebaseio.com',
  projectId        : 'sausage-cb48b',
  storageBucket    : 'sausage-cb48b.appspot.com',
  messagingSenderId: '318135072630'
};

firebase.initializeApp(config);
export const fdb = firebase.database();

export const model = {
  async uploadData(key: string, data: { [hash: string]: any }) {
    return fdb.ref(key).update(data);
  },
};
