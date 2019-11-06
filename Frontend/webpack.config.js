const path = require('path');
const webpack = require('webpack');

/*
 * SplitChunksPlugin is enabled by default and replaced
 * deprecated CommonsChunkPlugin. It automatically identifies modules which
 * should be splitted of chunk by heuristics using module duplication count and
 * module category (i. e. node_modules). And splits the chunks…
 *
 * It is safe to remove "splitChunks" from the generated configuration
 * and was added as an educational example.
 *
 * https://webpack.js.org/plugins/split-chunks-plugin/
 *
 */

const HtmlWebpackPlugin = require('html-webpack-plugin');

/*
 * We've enabled HtmlWebpackPlugin for you! This generates a html
 * page for you when you compile webpack, which will make you start
 * developing and prototyping faster.
 *
 * https://github.com/jantimon/html-webpack-plugin
 *
 */

module.exports = {
	mode: 'development',
	entry: './src/index.js',

	output: {
		filename: 'main.js',
		path: path.resolve(__dirname, 'dist'),
		publicPath: '/'
	},

	plugins: [
		new webpack.ProgressPlugin(),
		new HtmlWebpackPlugin()
	],

	module: {
		rules: [
			{
            			test: /\.(js|jsx)$/,
				exclude: /node_modules/,
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

	devServer: {
		inline: true,
		host: '0.0.0.0',
		disableHostCheck: true
	}
};
