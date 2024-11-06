from picamera import PiCamera
from PIL import Image,ImageDraw,ImageFont
from time import sleep
camera=Picamera()
filename="/home/pi/Desktop/image1.png"
camera.start_preview()
sleep(5)
camera.capture(filename)
camera.stop_preview()
camera.close()
image=Image.open(filename)
draw=Image.Draw(image)
font=ImageFont.load_defaut()
image_width=100
image_height=image.size
x=700 
y=700
text=" Hi, All"
draw.text((x,y),text,font=font,fill=(255,255,255))
image.save(filename)