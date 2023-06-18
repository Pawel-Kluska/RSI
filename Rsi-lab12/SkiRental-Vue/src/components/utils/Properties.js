export let apiLocation = '';

if (process.env.NODE_ENV === 'production') {
    // Production
    apiLocation = 'http://backend:8080';
} else {
    // Development
    apiLocation = 'http://localhost:8080';
}
