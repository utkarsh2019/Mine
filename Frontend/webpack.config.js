const HtmlWebPackPlugin = require( 'html-webpack-plugin' );
const path = require( 'path' );
const fs = require( 'fs' );
module.exports = {
	context: __dirname,
	entry: './src/index.js',
	output: {
		path: path.resolve( __dirname, 'dist' ),
		filename: 'main.js',
		publicPath: '/',
	},
	devServer: {
		inline: true,
		port: 443,
		host: '0.0.0.0',
		disableHostCheck: true,
		historyApiFallback: true,
		https: {
			key: fs.readFileSync('/etc/letsencrypt/live/mineapp.tech/privkey.pem'),
			cert: fs.readFileSync('/etc/letsencrypt/live/mineapp.tech/cert.pem'),
			ca: fs.readFileSync('/etc/letsencrypt/live/mineapp.tech/cert.pem'),
		}
	},
	module: {
		rules: [
			{
				test: /\.js$/,
				use: 'babel-loader',
			},
			{
				test: /\.css$/,
				use: ['style-loader', 'css-loader'],
			},
			{
				test: /\.(png|jpg|svg|gif)?$/,
				use: 'file-loader'
			}
		]
	},
	plugins: [
		new HtmlWebPackPlugin({
			template: path.resolve( __dirname, 'public/index.html' ),
			filename: 'index.html'
		})
	]
};
