import app from './app';

const port = process.env.PORT || '7002'; app.listen(port); 

console.log(`Listening on port ${port}`);