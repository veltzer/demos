#!/usr/bin/python

# demo of simple web server in python using HTTPServer
# originally grabbed from "http://fragments.turtlemeat.com/pythonwebserver.php".
# Copyright Jon Berg , turtlemeat.com

import string,cgi,time,os,BaseHTTPServer

class MyHandler(BaseHTTPServer.BaseHTTPRequestHandler):
	def handle_static(self,mimetype):
		f=open(self.realpath)
		#note that this potentially makes every file on your computer
		#readable by the internet. A real web server also checks that
		#the file that it is serving is inside into service "realm".
		self.send_response(200)
		self.send_header('Content-type',mimetype)
		self.end_headers()
		self.wfile.write(f.read())
		f.close()
	def handle_esp(self):
		self.send_response(200)
		self.send_header('Content-type','text/html')
		self.end_headers()
		self.wfile.write("today is the"+str(time.localtime()[7]))
		self.wfile.write("day in the year "+str(time.localtime()[0]))
	def handle_dir(self):
		self.send_response(200)
		self.send_header('Content-type','text/html')
		self.end_headers()
		self.wfile.write('<html><body>')
		for x in os.listdir(self.realpath):
			ref='http://localhost:8001'+self.path+x;
			self.wfile.write('<a href="'+ref+'">'+x+'</a><br/>')
		self.wfile.write('</body></html>')
	def get(self):
		# add a '.' to path to make it a local file path
		# / -> ./
		# /index.html -> ./index.html
		self.realpath='.'+self.path
		if(os.path.isfile(self.realpath)):
			#our dynamic content
			if self.path.endswith(".esp"):
				self.handle_esp();
				return
			#our static HTML content
			if self.path.endswith(".html"):
				self.handle_static('text/html');
				return
			#our static icons content
			if self.path.endswith(".ico"):
				self.handle_static('image/vnd.microsoft.icon');
				return
			#unrecognized file suffix
			self.send_error(500,'Unrecognized file type: %s' % self.path)
			return
		if(os.path.isdir(self.realpath)):
			self.handle_dir();
	def do_GET(self):
		# this is the method called by the framework... any lower level error
		# should send internal error to the client...
		try:
			self.get();
		except Exception, e:
			self.send_error(500,'Internal server error for resource: %s %s' % (self.path,e))
	def do_POST(self):
		try:
			ctype,pdict=cgi.parse_header(self.headers.getheader('content-type'))
			if ctype=='multipart/form-data':
				query=cgi.parse_multipart(self.rfile,pdict)
				self.send_response(301)
			self.end_headers()
			upfilecontent = query.get('upfile')
			print "filecontent", upfilecontent[0]
			self.wfile.write("<HTML>POST OK.<BR><BR>");
			self.wfile.write(upfilecontent[0]);
		except:
			pass

def main():
	try:
		port=8001
		print 'constructing server'
		server=BaseHTTPServer.HTTPServer(('',port),MyHandler)
		print 'started httpserver on port '+str(port)
		server.serve_forever()
	except KeyboardInterrupt:
		print '^C received, shutting down server'
		server.socket.close()

if __name__=='__main__':
	main()
