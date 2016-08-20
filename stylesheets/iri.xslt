<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:template name="word_to_iri">
	<xsl:param name="text" />
	<xsl:value-of select="concat(upper-case(substring($text,1,1)), substring($text, 2))"/>
</xsl:template>

<xsl:template name="verb_to_object_property">
	<xsl:param name="verb" />
	ObjectProperty(
	<xsl:value-of select="$verb/@lemma"/>
	<xsl:text> </xsl:text>
	<xsl:value-of select="$verb"/>)
</xsl:template>

<xsl:template name="verb_to_object_property_no_annotation">
	<xsl:param name="verb" />
	ObjectProperty(
	<xsl:value-of select="$verb/@lemma"/>)
</xsl:template>

<xsl:template name="noun_to_class">
	<xsl:param name="noun" />
	Class(
	<xsl:call-template name="word_to_iri">
		<xsl:with-param name="text" select="$noun/@lemma" />
	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:value-of select="$noun"/>)
</xsl:template>

<xsl:template name="noun_phrase_to_class">
   <xsl:param name="NP" />
   <xsl:choose>
      <xsl:when test="$NP/*[position()=1 and @det]">
      Class(
   		<xsl:for-each select="$NP/*[position()>1]/@lemma">
      		<xsl:call-template name="word_to_iri">
				<xsl:with-param name="text" select="." />
	   		</xsl:call-template>
   		</xsl:for-each>
   		<xsl:text> </xsl:text>
   		<xsl:for-each select="$NP/*[position()>1]">
      		<xsl:value-of select="."/>
	   		<xsl:text> </xsl:text>
   		</xsl:for-each>)
      </xsl:when>
      <xsl:otherwise>
      Class(
   		<xsl:for-each select="$NP/*/@lemma">
      		<xsl:call-template name="word_to_iri">
				<xsl:with-param name="text" select="." />
	   		</xsl:call-template>
   		</xsl:for-each>
   		<xsl:text> </xsl:text>
   		<xsl:for-each select="$NP/*">
      		<xsl:value-of select="."/>
	   		<xsl:text> </xsl:text>
   		</xsl:for-each>)
      </xsl:otherwise>
   </xsl:choose>
</xsl:template>

<xsl:template name="noun_phrase_to_class_with_has_prefix">
   <xsl:param name="NP" />
   <xsl:choose>
      <xsl:when test="$NP/*[position()=1 and @det]">
      Class(
   		has<xsl:for-each select="$NP/*[position()>1]/@lemma">
      		<xsl:call-template name="word_to_iri">
                    <xsl:with-param name="text" select="." />
                </xsl:call-template>
   		</xsl:for-each>
   		<xsl:text> </xsl:text>
   		<xsl:for-each select="$NP/*[position()>1]">
                has
      		<xsl:value-of select="."/>
                    <xsl:text> </xsl:text>
   		</xsl:for-each>)
      </xsl:when>
      <xsl:otherwise>
      Class(
   		<xsl:for-each select="$NP/*/@lemma">
      		<xsl:call-template name="word_to_iri">
                    <xsl:with-param name="text" select="." />
	   	</xsl:call-template>
   		</xsl:for-each>
   		<xsl:text> </xsl:text>
   		<xsl:for-each select="$NP/*">
                has
      		<xsl:value-of select="."/>
                    <xsl:text> </xsl:text>
   		</xsl:for-each>)
      </xsl:otherwise>
   </xsl:choose>
</xsl:template>

<xsl:template name="noun_to_individual">
	<xsl:param name="noun" />
	Individual(		
	<xsl:value-of select="$noun"/>
	<xsl:text> </xsl:text>
	<xsl:value-of select="$noun"/>)
</xsl:template>

<xsl:template name="noun_to_individual_no_annotation">
	<xsl:param name="noun" />
	Individual(		
	<xsl:value-of select="$noun"/>)
</xsl:template>

<xsl:template name="noun_phrase_to_individual">
   <xsl:param name="NP" />
   <xsl:choose>
      <xsl:when test="$NP/*[position()=1 and @det]">
      Individual(
   		<xsl:for-each select="$NP/*[position()>1]">
                    <xsl:value-of select="."/>
   		</xsl:for-each>
   		<xsl:text> </xsl:text>
   		<xsl:for-each select="$NP/*[position()>1]">
                    <xsl:value-of select="."/>
                    <xsl:text> </xsl:text>
   		</xsl:for-each>)
      </xsl:when>
      <xsl:otherwise>
      Individual(
   		<xsl:for-each select="$NP/*">
                    <xsl:value-of select="."/>
   		</xsl:for-each>
   		<xsl:text> </xsl:text>
   		<xsl:for-each select="$NP/*">
                    <xsl:value-of select="."/>
                    <xsl:text> </xsl:text>
   		</xsl:for-each>)
      </xsl:otherwise>
   </xsl:choose>
</xsl:template>

<xsl:template name="noun_phrase_to_individual_no_annotation">
   <xsl:param name="NP" />
   <xsl:choose>
      <xsl:when test="$NP/*[position()=1 and @det]">
      Individual(
   		<xsl:for-each select="$NP/*[position()>1]">
                    <xsl:value-of select="."/>
   		</xsl:for-each>)
      </xsl:when>
      <xsl:otherwise>
      Individual(
   		<xsl:for-each select="$NP/*">
                    <xsl:value-of select="."/>
   		</xsl:for-each>)
      </xsl:otherwise>
   </xsl:choose>
</xsl:template>

</xsl:stylesheet>